package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.response.SignUpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpResponseTest {
    @Test
    void getPassword(){
        SignUpResponse response = SignUpResponse.builder()
                .password("Abv@123")
                .build();
        assertEquals(response.getPassword(),"Abv@123");
    }
    @Test
    void getEmail(){
        SignUpResponse response = SignUpResponse.builder()
                .email("sree54@gmail.com")
                .build();
        assertEquals(response.getEmail(),"sree54@gmail.com");
    }
    @Test
    void getName(){
        SignUpResponse response = SignUpResponse.builder()
                .name("Sreejith")
                .build();
        assertEquals(response.getName(),"Sreejith");
    }
}
