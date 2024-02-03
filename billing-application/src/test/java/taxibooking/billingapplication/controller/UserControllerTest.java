package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.response.SignUpResponse;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testSignUp() throws Exception {

        SignUpRequest signupRequest = new SignUpRequest("Sreejith", "sirew@Gmail.com", "Sihg@123");
        User user =
                User.builder()
                        .name(signupRequest.getName())
                        .email(signupRequest.getEmail())
                        .password(signupRequest.getPassword())
                        .build();

        SignUpResponse expectedResponse =
                SignUpResponse.builder().email(user.getEmail()).name(user.getName()).build();
        when(userService.signUp(any(SignUpRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(
                        post("/v1/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{\"email\":\"sirew@Gmail.com\",\"name\":\"Sreejith\",\"password\":\"Sihg@123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }
}
