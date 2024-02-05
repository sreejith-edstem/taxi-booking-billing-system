package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.TaxiRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiRequestTest {
    @Test
    void getDriverName(){
        TaxiRequest request = TaxiRequest.builder()
                .driverName("Aswanth")
                .build();
        assertEquals(request.getDriverName(),"Aswanth");
    }
    @Test
    void getLicenceNumber(){
        TaxiRequest request = TaxiRequest.builder()
                .licenseNumber("KL-20w-3456")
                .build();
        assertEquals(request.getLicenseNumber(),"KL-20w-3456");
    }
    @Test
    void getCurrentLocation(){
        TaxiRequest request = TaxiRequest.builder()
                .currentLocation("Aluva")
                .build();
        assertEquals(request.getCurrentLocation(),"Aluva");
    }
}
