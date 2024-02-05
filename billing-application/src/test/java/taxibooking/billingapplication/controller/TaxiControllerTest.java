package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import taxibooking.billingapplication.service.TaxiService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private TaxiService taxiService;
    @Autowired
    private TaxiController taxiController;
}
