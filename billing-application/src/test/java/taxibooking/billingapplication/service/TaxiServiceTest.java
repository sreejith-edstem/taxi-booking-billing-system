package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import taxibooking.billingapplication.repository.TaxiRepository;

public class TaxiServiceTest {
    private TaxiRepository taxiRepository;
    private ModelMapper modelMapper;
    private TaxiService taxiService = new TaxiService(null, null);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        taxiRepository = Mockito.mock(TaxiRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        taxiService = new TaxiService(taxiRepository, modelMapper);
    }
    //    @Test
    //    void testAddTaxi(){
    //        Taxi taxi = new Taxi();
    //        TaxiRequest request = new TaxiRequest();
    //        when(modelMapper.map(request, Taxi.class)).thenReturn(taxi);
    //        when(taxiRepository.save(taxi)).thenReturn(taxi);
    //        Long result = taxiService.addTaxi(request);
    //        assertEquals(taxi,result);
    //    }
}
