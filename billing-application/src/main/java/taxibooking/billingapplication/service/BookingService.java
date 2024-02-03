package taxibooking.billingapplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import taxibooking.billingapplication.constant.Status;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.exception.BookingNotFoundException;
import taxibooking.billingapplication.exception.UserNotFoundException;
import taxibooking.billingapplication.model.Booking;
import taxibooking.billingapplication.model.Taxi;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.repository.BookingRepository;
import taxibooking.billingapplication.repository.TaxiRepository;
import taxibooking.billingapplication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;

    private static final double RATE_PER_KM = 0.5;

    public BookingResponse createBooking(long userId, BookingRequest request) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Taxi> availableTaxis = searchNearestTaxi(request.getPickupLocation());
        if (availableTaxis.isEmpty()) {
            throw new RuntimeException("No taxis available at the pickup location");
        }
        Taxi nearestTaxi = availableTaxis.get(0);

        Booking booking =
                Booking.builder()
                        .userId(user)
                        .taxiId(nearestTaxi)
                        .bookingTime(LocalDateTime.now())
                        .fare(request.getFare())
                        .pickupLocation(request.getPickupLocation())
                        .dropOffLocation(request.getDropOffLocation())
                        .status(Status.CONFIRMED)
                        .build();
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingResponse.class);
    }

    public List<Taxi> searchNearestTaxi(String pickupLocation) {
        List<Taxi> availableTaxis =
                taxiRepository.findAll().stream()
                        .filter(taxi -> taxi.getCurrentLocation().equals(pickupLocation))
                        .collect(Collectors.toList());
        if (availableTaxis.isEmpty()) {
            throw new RuntimeException("No taxis available at the pickup location");
        }
        return availableTaxis.stream()
                .map(taxi -> modelMapper.map(taxi, Taxi.class))
                .collect(Collectors.toList());
    }

    public BookingResponse viewBookingDetailsById(long id) {
        Booking booking =
                bookingRepository
                        .findById(id)
                        .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        return modelMapper.map(booking, BookingResponse.class);
    }

    public List<BookingResponse> viewAllBookingDetails() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingResponse.class))
                .collect(Collectors.toList());
    }

    public void fareCalculation(long userId, double distance) {
        Optional<User> user = userRepository.findById(userId);
        double accountBalance = user.get().getAccountBalance();
        double fare = distance * RATE_PER_KM;

        if (accountBalance >= fare) {
            double newBalance = accountBalance - fare;
            User user1 =
                    User.builder()
                            .id(user.get().getId())
                            .name(user.get().getName())
                            .email(user.get().getEmail())
                            .password(user.get().getPassword())
                            .accountBalance(newBalance)
                            .build();
            userRepository.save(user1);
            System.out.println("Ride completed");
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public long cancelBooking(long bookingId) {
        Booking booking =
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        booking =
                Booking.builder()
                        .id(booking.getId())
                        .bookingTime(booking.getBookingTime())
                        .pickupLocation(booking.getPickupLocation())
                        .dropOffLocation(booking.getDropOffLocation())
                        .fare(booking.getFare())
                        .status(Status.CANCELLED)
                        .taxiId(booking.getTaxiId())
                        .userId(booking.getUserId())
                        .build();
        bookingRepository.save(booking);
        return bookingId;
    }
}
