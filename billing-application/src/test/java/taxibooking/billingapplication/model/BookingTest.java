package taxibooking.billingapplication.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookingTest {
    @Test
    void getPickupLocation(){
        Booking booking = Booking.builder()
                .pickupLocation("Trivandrum")
                .build();
        assertEquals(booking.getPickupLocation(),"Trivandrum");
    }
    @Test
    void getDropOffLocation(){
        Booking booking = Booking.builder()
                .dropOffLocation("Kochi")
                .build();
        assertEquals(booking.getDropOffLocation(),"Kochi");
    }
    @Test
    void getFare(){
        Booking booking = Booking.builder()
                .fare(5214)
                .build();
        assertEquals(booking.getFare(),5214);
    }

    @Test
    public void testDefaultConstructor() {
        Booking testBooking = new Booking();

        assertNotNull(testBooking);
    }
}
