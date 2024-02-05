package taxibooking.billingapplication.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void checkConstructor() {
        Taxi testTaxi = new Taxi(1L,"devan","KL-20R-5468","Kakkand");
        Taxi checkTaxi = Taxi.builder()
                .id(1L)
                .driverName("devan")
                .licenseNumber("KL-20R-5468")
                .currentLocation("Kakkand")
                .build();

        assertEquals(testTaxi.getDriverName(), checkTaxi.getDriverName());
        assertEquals(testTaxi.getLicenseNumber(), checkTaxi.getLicenseNumber());
        assertEquals(testTaxi.getCurrentLocation(), checkTaxi.getCurrentLocation());
    }

    @Test
    public void testDefaultConstructor() {
        Taxi testTaxi = new Taxi();

        assertNotNull(testTaxi);
    }
}
