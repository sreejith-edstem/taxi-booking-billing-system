package taxibooking.billingapplication.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;

    public BookingResponse createBooking(long userId,long taxiId, BookingRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new RuntimeException("Taxi not found"));
        Booking booking = Booking.builder()
                .userId(user)
                .taxiId(taxi)
                .bookingTime(LocalDateTime.now())
                .fare(request.getFare())
                .pickupLocation(request.getPickupLocation())
                .dropOffLocation(request.getDropOffLocation())
                .status(request.getStatus())
                .build();
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingResponse.class);
    }
    public List<BookingResponse> viewBookingDetails(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingResponse.class))
                .collect(Collectors.toList());
    }
    public void cancelBooking(long id){
        if (!bookingRepository.existsById(id)){
            throw new RuntimeException("Booking not found");
        }
        bookingRepository.deleteById(id);
    }

}
