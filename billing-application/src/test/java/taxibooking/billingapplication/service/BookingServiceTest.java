package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        bookingService = new BookingService(bookingRepository, userRepository, taxiRepository, modelMapper);
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
    void testViewAllBookingDetails(){
        List<Booking> expectedBookings = Arrays.asList(new Booking(), new Booking());
        when(bookingRepository.findAll()).thenReturn(expectedBookings);
        List<BookingResponse> actualBookings = bookingService.viewAllBookingDetails();
        assertEquals(expectedBookings, actualBookings);
        verify(bookingRepository, times(1)).findAll();
    }
    @Test
    void testCancelBooking() {
        long id = 1L;
        when(bookingRepository.existsById(id)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> bookingService.cancelBooking(id));
    }

    @Test
    void testConfirmBooking() {
        Long bookingId = 1L;

        Booking booking = new Booking();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        Long returnedBookingId = bookingService.confirmBooking(bookingId);

        assertEquals(bookingId, returnedBookingId);
        verify(bookingRepository, times(1)).findById(bookingId);
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }
}
