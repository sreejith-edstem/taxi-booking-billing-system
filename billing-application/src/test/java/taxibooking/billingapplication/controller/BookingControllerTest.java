package taxibooking.billingapplication.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import taxibooking.billingapplication.service.BookingService;

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
}
