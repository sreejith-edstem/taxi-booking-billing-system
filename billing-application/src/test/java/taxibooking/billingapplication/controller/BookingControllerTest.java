package taxibooking.billingapplication.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.service.BookingService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private BookingService bookingService;

    @Test
    void testViewBookingDetailsById() throws Exception {
        Long bookingId = 1L;
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/user/booking/" + bookingId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void testCreateBooking() throws Exception{
        long userId = 1L;
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();

        when(bookingService.createBooking(userId, request)).thenReturn(response);

        mockMvc.perform(post("/v1/user/booking/{userId}/create", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }
    @Test
    public void testViewAllBookingDetails() throws Exception {
        BookingResponse response1 = new BookingResponse();
        BookingResponse response2 = new BookingResponse();
        List<BookingResponse> responses = Arrays.asList(response1, response2);

        when(bookingService.viewAllBookingDetails()).thenReturn(responses);

        mockMvc.perform(get("/v1/user/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testFareCalculation() throws Exception {
        long userId = 1L;
        double distance = 10.0;

        doNothing().when(bookingService).fareCalculation(userId, distance);

        mockMvc.perform(post("/v1/user/booking/{userId}/fare", userId)
                        .param("distance", String.valueOf(distance))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testCancelBooking() throws Exception {
        long id = 1L;

        when(bookingService.cancelBooking(id)).thenReturn(id);

        mockMvc.perform(post("/v1/user/booking/cancel/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
