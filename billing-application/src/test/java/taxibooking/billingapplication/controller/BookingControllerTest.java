package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import taxibooking.billingapplication.constant.Status;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.service.BookingService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookingControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean
    private BookingService bookingService;

    @Test
    void testViewBookingDetailsById() throws Exception {
        Long bookingId = 1L;
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/v1/user/booking/" + bookingId)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateBooking() throws Exception {
        long userId = 1L;
        BookingRequest bookingRequest = new BookingRequest("Aluva", "Kakkanad", 50.5, Status.CONFIRMED);
        BookingResponse bookingResponse = new BookingResponse(1L, "Aluva", "Kakkanad", 50.5, Status.CONFIRMED);

        when(bookingService.createBooking(anyLong(), any(BookingRequest.class))).thenReturn(bookingResponse);

        mockMvc.perform(post("/v1/user/booking/{userId}/create", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingRequest)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookingResponse)));
    }







    // verify(bookingService, times(1)).createBooking(userId, request);
    @Test
    public void testViewAllBookingDetails() throws Exception {
        List<BookingResponse> expectedResponses = Arrays.asList(new BookingResponse(), new BookingResponse());

        when(bookingService.viewAllBookingDetails()).thenReturn(expectedResponses);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/user/booking")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponses)));
    }
    @Test
    public void testFareCalculation() throws Exception {
        long userId = 123L;
        double distance = 10.5;

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

        mockMvc.perform(post("/v1/user/booking/cancel/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
