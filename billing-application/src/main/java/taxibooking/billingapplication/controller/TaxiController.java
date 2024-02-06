package taxibooking.billingapplication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.service.TaxiService;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TaxiController {
    private final TaxiService taxiService;

    @PostMapping("/taxi/create")
    public Long addTaxi(@Valid @RequestBody TaxiRequest request) {
        return taxiService.addTaxi(request);
    }
}
