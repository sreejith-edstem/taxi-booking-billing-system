package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.constant.Status;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void testCheckConstructor() {
        BookingResponse testResponse = new BookingResponse(1L,"aluva","ernakulam",854, Status.CONFIRMED);
        BookingResponse checkResponse = BookingResponse.builder()
                .id(1L)
                .pickUpLocation("aluva")
                .dropOffLocation("ernakulam")
                .fare(854)
                .status(Status.CONFIRMED)
                .build();

        assertEquals(testResponse.getId(), checkResponse.getId());
        assertEquals(testResponse.getPickUpLocation(), checkResponse.getPickUpLocation());
        assertEquals(testResponse.getDropOffLocation(), checkResponse.getDropOffLocation());
        assertEquals(testResponse.getFare(), checkResponse.getFare());
        assertEquals(testResponse.getStatus(), checkResponse.getStatus());
    }
    @Test
    public void testDefaultConstructor() {
        BookingResponse response = new BookingResponse();

        assertNotNull(response);
    }
}
