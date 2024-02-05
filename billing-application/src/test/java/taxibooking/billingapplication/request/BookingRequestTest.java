package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.BookingRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingRequestTest {
    @Test
    void getPickupLocation(){
        BookingRequest request = BookingRequest.builder()
                .pickupLocation("Trivandrum")
                .build();
        assertEquals(request.getPickupLocation(),"Trivandrum");
    }
    @Test
    void getDropOffLocation(){
        BookingRequest request = BookingRequest.builder()
                .dropOffLocation("Kochi")
                .build();
        assertEquals(request.getDropOffLocation(),"Kochi");
    }
    @Test
    void getFare(){
        BookingRequest request = BookingRequest.builder()
                .fare(5214)
                .build();
        assertEquals(request.getFare(),5214);
    }
}
