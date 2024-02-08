package taxibooking.billingapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
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

public class BookingServiceTest {
    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private TaxiRepository taxiRepository;
    private ModelMapper modelMapper;
    private BookingService bookingService = new BookingService(null, null, null, null);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        bookingRepository = Mockito.mock(BookingRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        taxiRepository = Mockito.mock(TaxiRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        bookingService =
                new BookingService(bookingRepository, userRepository, taxiRepository, modelMapper);
    }

    @Test
    void testViewBookingDetailsById() {
        long id = 1L;
        Booking employee = new Booking();
        BookingResponse response = new BookingResponse();
        when(bookingRepository.findById(id)).thenReturn(Optional.of(employee));
        when(modelMapper.map(employee, BookingResponse.class)).thenReturn(response);
        BookingResponse result = bookingService.viewBookingDetailsById(id);
        assertEquals(result, response);
        verify(bookingRepository, times(1)).findById(id);
    }
    @Test
    public void testViewBookingDetailsById_BookingNotFound() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        Long bookingId = 1L;

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.viewBookingDetailsById(bookingId);
        });

        verify(bookingRepository, times(1)).findById(bookingId);

        verifyNoMoreInteractions(modelMapper);
    }

    @Test
    void testViewAllBookingDetails() {
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        BookingResponse bookingResponse1 = new BookingResponse();
        BookingResponse bookingResponse2 = new BookingResponse();
        List<BookingResponse> expectedResponses = Arrays.asList(bookingResponse1, bookingResponse2);

        when(bookingRepository.findAll()).thenReturn(bookings);
        when(modelMapper.map(booking1, BookingResponse.class)).thenReturn(bookingResponse1);
        when(modelMapper.map(booking2, BookingResponse.class)).thenReturn(bookingResponse2);

        List<BookingResponse> actualResponses = bookingService.viewAllBookingDetails();

        assertEquals(expectedResponses, actualResponses);
        verify(bookingRepository, times(1)).findAll();
        verify(modelMapper, times(1)).map(booking1, BookingResponse.class);
        verify(modelMapper, times(1)).map(booking2, BookingResponse.class);
    }

    @Test
    void testCancelBooking() {
        Long bookingId = 1L;

        Booking booking = new Booking();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        Long returnedBookingId = bookingService.cancelBooking(bookingId);

        assertEquals(bookingId, returnedBookingId);
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
    @Test
    public void testCancelBooking_BookingNotFound() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        Long bookingId = 1L;

        assertThrows(BookingNotFoundException.class, () -> {
            bookingService.cancelBooking(bookingId);
        });

        verify(bookingRepository, times(1)).findById(bookingId);

        verifyNoMoreInteractions(bookingRepository);
    }

    @Test
    public void testSearchNearestTaxi() {
        String pickupLocation = "Test Location";
        Taxi taxi1 = Taxi.builder().currentLocation(pickupLocation).build();
        Taxi taxi2 = Taxi.builder().currentLocation(pickupLocation).build();
        List<Taxi> taxis = Arrays.asList(taxi1, taxi2);

        when(taxiRepository.findAll()).thenReturn(taxis);
        when(modelMapper.map(any(Taxi.class), eq(Taxi.class))).thenAnswer(i -> i.getArguments()[0]);

        List<Taxi> actualTaxis = bookingService.searchNearestTaxi(pickupLocation);

        assertEquals(taxis, actualTaxis);
        verify(taxiRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(Taxi.class), eq(Taxi.class));
    }

    @Test
    public void testSearchNearestTaxi_NoTaxisAvailable() {
        String pickupLocation = "Test Location";
        List<Taxi> taxis = Collections.emptyList();

        when(taxiRepository.findAll()).thenReturn(taxis);

        assertThrows(RuntimeException.class, () -> bookingService.searchNearestTaxi(pickupLocation));
        verify(taxiRepository, times(1)).findAll();
    }

    @Test
    void testCreateBooking() {
        long userId = 1L;
        BookingRequest request = BookingRequest.builder()
                .pickupLocation("Test Location")
                .fare(100.0)
                .dropOffLocation("Test DropOff Location")
                .build();


        User user = User.builder().id(userId).build();
        Taxi taxi = Taxi.builder().currentLocation(request.getPickupLocation()).build();
        List<Taxi> taxis = Arrays.asList(taxi);

        Booking booking = Booking.builder()
                .userId(user)
                .taxiId(taxi)
                .bookingTime(LocalDateTime.now())
                .fare(request.getFare())
                .pickupLocation(request.getPickupLocation())
                .dropOffLocation(request.getDropOffLocation())
                .status(Status.CONFIRMED)
                .build();

        Booking savedBooking = Booking.builder().build();
        BookingResponse expectedResponse = BookingResponse.builder().build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(taxiRepository.findAll()).thenReturn(taxis);
        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
        when(modelMapper.map(savedBooking, BookingResponse.class)).thenReturn(expectedResponse);

        BookingResponse actualResponse = bookingService.createBooking(userId, request);

        assertEquals(expectedResponse, actualResponse);
        verify(userRepository, times(1)).findById(userId);
        verify(taxiRepository, times(1)).findAll();
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(modelMapper, times(1)).map(savedBooking, BookingResponse.class);
    }
    @Test
    public void testCreateBooking_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Long userId = 1L;
        BookingRequest request = new BookingRequest("Ernakulam","Aluva",546);

        assertThrows(UserNotFoundException.class, () -> {
            bookingService.createBooking(userId, request);
        });

        verify(userRepository, times(1)).findById(userId);

        verifyNoMoreInteractions(bookingRepository, modelMapper);
    }

    @Test
    void testFareCalculation() {
        long userId = 1L;
        double distance = 10.0;
        double RATE_PER_KM = 5.0;
        double accountBalance = 100.0;
        double fare = distance * RATE_PER_KM;
        double newBalance = accountBalance - fare;

        User user = User.builder()
                .id(userId)
                .accountBalance(accountBalance)
                .build();

        User updatedUser = User.builder()
                .id(userId)
                .accountBalance(newBalance)
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        bookingService.fareCalculation(userId, distance);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }


}
