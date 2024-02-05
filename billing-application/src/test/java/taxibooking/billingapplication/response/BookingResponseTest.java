package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.response.BookingResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingResponseTest {
    @Test
    void getPickupLocation(){
        BookingResponse response = BookingResponse.builder()
                .pickUpLocation("Trivandrum")
                .build();
        assertEquals(response.getPickUpLocation(),"Trivandrum");
    }
    @Test
    void getDropOffLocation(){
        BookingResponse response = BookingResponse.builder()
                .dropOffLocation("Kochi")
                .build();
        assertEquals(response.getDropOffLocation(),"Kochi");
    }
    @Test
    void getFare(){
        BookingResponse response = BookingResponse.builder()
                .fare(5214)
                .build();
        assertEquals(response.getFare(),5214);
    }
}
