package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.service.BookingService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        BookingResponse expectedResponse = new BookingResponse();

        when(bookingService.createBooking(userId, request)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/booking/{userId}/create", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponse)));

        verify(bookingService, times(1)).createBooking(userId, request);
    }
    @Test
    public void testViewAllBookingDetails() throws Exception {
        BookingResponse response1 = new BookingResponse();
        List<BookingResponse> expectedResponses = Arrays.asList(new BookingResponse(), new BookingResponse());

        when(bookingService.viewAllBookingDetails()).thenReturn(expectedResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedResponses)));

        verify(bookingService, times(1)).viewAllBookingDetails();
    }
    @Test
    public void testFareCalculation() throws Exception {
        long userId = 1L;
        double distance = 10.0;

        doNothing().when(bookingService).fareCalculation(userId, distance);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/booking/{userId}/fare", userId)
                        .param("distance", String.valueOf(distance))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(bookingService, times(1)).fareCalculation(userId, distance);
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
