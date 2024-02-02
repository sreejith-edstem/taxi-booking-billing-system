package taxibooking.billingapplication.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import taxibooking.billingapplication.constant.Status;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.model.Booking;
import taxibooking.billingapplication.model.Taxi;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.repository.BookingRepository;
import taxibooking.billingapplication.repository.TaxiRepository;
import taxibooking.billingapplication.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;

    private static final double RATE_PER_KM = 0.5;

    public BookingResponse createBooking(long userId, long taxiId, BookingRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new RuntimeException("Taxi not found"));
//        Taxi taxi1 = (Taxi) taxiRepository.findNearestAvailableTaxi(request.getPickupLocation())
//                .orElseThrow(() -> new RuntimeException("No available taxis"));
        Booking booking = Booking.builder()
                .userId(user)
                .taxiId(taxi)
                .bookingTime(LocalDateTime.now())
                .fare(request.getFare())
                .pickupLocation(request.getPickupLocation())
                .dropOffLocation(request.getDropOffLocation())
                .status(Status.PENDING)
                .build();
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingResponse.class);
    }
    public BookingResponse viewBookingDetailsById(long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return modelMapper.map(booking, BookingResponse.class);
    }

    public List<BookingResponse> viewAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingResponse.class))
                .collect(Collectors.toList());
    }

    public void cancelBooking(long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }

    public void completedTrip(long userId, double distance) {
        Optional<User> user = userRepository.findById(userId);
        double accountBalance = user.get().getAccountBalance();
        double fare = distance * RATE_PER_KM;
        if (accountBalance >= fare) {
            double newBalance = accountBalance - fare;
            User user1 = User.builder()
                    .accountBalance(newBalance)
                    .build();
            userRepository.save(user1);
            System.out.println("Ride completed");
        } else {
            System.out.println("Insufficient balance");
        }
    }
//public BookingResponse book(Long userId, Long taxiId, Long distance, BookingRequest bookingRequest) {
//    Taxi taxi = taxiRepository.findById(taxiId).orElseThrow(() -> new RuntimeException("Taxi not found"));
//    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
//    Double minimumCharge = 10.00;
//    Double fare = distance * minimumCharge;
//
//    Booking booking = Booking.builder()
//            .pickupLocation(bookingRequest.getPickupLocation())
//            .dropOffLocation(bookingRequest.getDropOffLocation())
//            .fare(fare)
//            .taxiId(taxi.getTaxiId())
//            .userId(user.getUserId())
//            .bookingTime(LocalDateTime.now())
//            .status(Status.CONFIRMED)
//            .build();
//    booking = bookingRepository.save(booking);
//    return modelMapper.map(booking, BookingResponse.class);
//}
}