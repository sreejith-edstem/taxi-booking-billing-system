package taxibooking.billingapplication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.service.TaxiService;

@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TaxiService taxiService;

    @Test
    void testAddTaxi() throws Exception {
        String json = "{\"driverName\": \"licenseNumber\":\"currentLocation\"}";
        Long taxiId = 1L;
        when(taxiService.addTaxi(any(TaxiRequest.class))).thenReturn(taxiId);
        mockMvc.perform(
                        post("/v1/taxi/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(taxiId));
        verify(taxiService, times(1)).addTaxi(any(TaxiRequest.class));
    }
}
