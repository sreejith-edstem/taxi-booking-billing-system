package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.SignUpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpRequestTest {
    @Test
    void getPassword(){
        SignUpRequest request = SignUpRequest.builder()
                .password("Abv@123")
                .build();
        assertEquals(request.getPassword(),"Abv@123");
    }
    @Test
    void getEmail(){
        SignUpRequest request = SignUpRequest.builder()
                .email("sree54@gmail.com")
                .build();
        assertEquals(request.getEmail(),"sree54@gmail.com");
    }
    @Test
    void getName(){
        SignUpRequest request = SignUpRequest.builder()
                .name("Sreejith")
                .build();
        assertEquals(request.getName(),"Sreejith");
    }
}
