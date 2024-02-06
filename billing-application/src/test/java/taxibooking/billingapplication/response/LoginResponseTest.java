package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.contract.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginResponseTest {
    @Test
    void getToken(){
        LoginResponse response = LoginResponse.builder()
                .token("1256546LKJIUYTFGTREDS")
                .build();
        assertEquals(response.getToken(),"1256546LKJIUYTFGTREDS");
    }
    @Test
    public void testCheckConstructor() {
        LoginResponse response = new LoginResponse("1256546LKJIUYTFGTREDS");
        LoginResponse checkResponse = LoginResponse.builder()
                .token("1256546LKJIUYTFGTREDS")
                .build();
        assertEquals(response.getToken(), checkResponse.getToken());
    }

    @Test
    public void testDefaultConstructor() {
        LoginRequest request = new LoginRequest();

        assertNotNull(request);
    }
}
