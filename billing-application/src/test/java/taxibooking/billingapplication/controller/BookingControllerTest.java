package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import taxibooking.billingapplication.repository.BookingRepository;
import taxibooking.billingapplication.service.BookingService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookingService bookingService;
    @Test
    void testCreateBooking() throws Exception{
        String json = "{\"title\":\"Test Note\",\"content\":\"This is a test note.\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/user/booking/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }

}
