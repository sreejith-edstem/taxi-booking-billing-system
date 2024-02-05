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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TaxiService taxiService;

    @Test
    public void testAddTaxi() throws Exception {
        TaxiRequest request = new TaxiRequest();
        Long expectedResponse = 1L;

        when(taxiService.addTaxi(any(TaxiRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(post("/v1/taxi/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse.toString()));

        verify(taxiService, times(1)).addTaxi(any(TaxiRequest.class));
    }
}
