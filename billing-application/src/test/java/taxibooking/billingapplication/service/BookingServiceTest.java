package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
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
        bookingService = new BookingService(bookingRepository, userRepository, taxiRepository, modelMapper);
    }

}
