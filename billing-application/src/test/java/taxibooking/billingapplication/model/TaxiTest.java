package taxibooking.billingapplication.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiTest {
    @Test
    void getDriverName(){
        Taxi taxi = Taxi.builder()
                .driverName("Sharok")
                .build();
        assertEquals(taxi.getDriverName(),"Sharok");
    }
    @Test
    void getLicenceNumber(){
        Taxi taxi = Taxi.builder()
                .licenseNumber("KL-20w-3456")
                .build();
        assertEquals(taxi.getLicenseNumber(),"KL-20w-3456");
    }
    @Test
    void getCurrentLocation(){
        Taxi taxi = Taxi.builder()
                .currentLocation("Aluva")
                .build();
        assertEquals(taxi.getCurrentLocation(),"Aluva");
    }
}
