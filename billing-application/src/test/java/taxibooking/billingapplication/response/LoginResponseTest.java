package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseTest {
    @Test
    void getToken(){
        LoginResponse response = LoginResponse.builder()
                .token("1256546LKJIUYTFGTREDS")
                .build();
        assertEquals(response.getToken(),"1256546LKJIUYTFGTREDS");
    }
}
