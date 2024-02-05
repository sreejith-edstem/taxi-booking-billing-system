package taxibooking.billingapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.request.UpdateAccountRequest;
import taxibooking.billingapplication.contract.response.LoginResponse;
import taxibooking.billingapplication.contract.response.SignUpResponse;
import taxibooking.billingapplication.contract.response.UpdateAccountResponse;
import taxibooking.billingapplication.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testSignUp() throws Exception {

        SignUpRequest signupRequest = new SignUpRequest("Midun", "midun@Gmail.com", "Midun@gmail.com");
        SignUpResponse expectedResponse = new SignUpResponse(1L,"Midun", "midun@Gmail.com", "Midun@gmail.com");


        when(userService.signUp(any(SignUpRequest.class))).thenReturn(expectedResponse);
        mockMvc.perform(
                        post("/v1/user/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(signupRequest)))
                .andExpect(status().isOk());
    }
    @Test
    public void testLogin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        LoginResponse loginResponse = new LoginResponse("JWT_TOKEN");

        when(userService.authenticate(loginRequest)).thenReturn(loginResponse);

        mockMvc.perform(post("/v1/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"username\", \"password\":\"password\"}"))
                .andExpect(status().isOk());
    }
    @Test
    public void testUpdateBalance() throws Exception {
        long userId = 1L;
        UpdateAccountRequest request = new UpdateAccountRequest();
        UpdateAccountResponse response = new UpdateAccountResponse();

        when(userService.updateBalance(eq(userId), any(UpdateAccountRequest.class))).thenReturn(response);

        mockMvc.perform(put("/v1/user/" + userId + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateBalance(eq(userId), any(UpdateAccountRequest.class));
    }
}
