package taxibooking.billingapplication.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.model.Taxi;
import taxibooking.billingapplication.repository.TaxiRepository;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;

    public Long createTaxi(TaxiRequest request){
        Taxi taxi = taxiRepository.save(modelMapper.map(request, Taxi.class));
        return taxi.getId();
    }
}
