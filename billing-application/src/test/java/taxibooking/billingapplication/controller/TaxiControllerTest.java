package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.service.TaxiService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TaxiService taxiService;

    @Test
    public void testAddTaxi() throws Exception {
        TaxiRequest taxiRequest = new TaxiRequest("Dathan","KL-20R-5117","Aluva");

        when(taxiService.addTaxi(any(TaxiRequest.class))).thenReturn(1L);

        mockMvc.perform(post("/v1/taxi/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taxiRequest)))
                .andExpect(status().isOk());
    }
}
