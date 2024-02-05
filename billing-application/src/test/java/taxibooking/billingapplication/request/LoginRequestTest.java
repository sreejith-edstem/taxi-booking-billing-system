package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.contract.request.SignUpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestTest {
    @Test
    void getPassword(){
        LoginRequest request = LoginRequest.builder()
                .password("Abv@123")
                .build();
        assertEquals(request.getPassword(),"Abv@123");
    }
    @Test
    void getEmail(){
        LoginRequest request = LoginRequest.builder()
                .email("sree54@gmail.com")
                .build();
        assertEquals(request.getEmail(),"sree54@gmail.com");
    }
}
