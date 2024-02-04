package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.model.Taxi;
import taxibooking.billingapplication.repository.TaxiRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        @Test
        void testAddTaxi(){
            TaxiRequest request = new TaxiRequest();
            Taxi taxi = Taxi.builder()
                    .id(1L)
                    .build();

            when(modelMapper.map(request, Taxi.class)).thenReturn(taxi);
            when(taxiRepository.save(taxi)).thenReturn(taxi);

            Long taxiId = taxiService.addTaxi(request);

            assertEquals(taxi.getId(), taxiId);
            verify(modelMapper, times(1)).map(request, Taxi.class);
            verify(taxiRepository, times(1)).save(taxi);
        }
}
