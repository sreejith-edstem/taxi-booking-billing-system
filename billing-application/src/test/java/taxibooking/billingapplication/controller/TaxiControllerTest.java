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
import taxibooking.billingapplication.contract.request.TaxiRequest;
import taxibooking.billingapplication.service.TaxiService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean
    private TaxiService taxiService;


    @Test
    public void testAddTaxi() throws Exception {
        TaxiRequest request = new TaxiRequest("sharok","KL-20F-9087","aluva"); // populate this as necessary

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/taxi/create")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(taxiService).addTaxi(any(TaxiRequest.class));
    }
}
