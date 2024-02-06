package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void testCheckConstructor() {
        LoginRequest testRequest = new LoginRequest("joe@gmail.com","Joe@123");
        LoginRequest checkRequest = LoginRequest.builder()
                .email("joe@gmail.com")
                .password("Joe@123")
                .build();

        assertEquals(testRequest.getEmail(), checkRequest.getEmail());
        assertEquals(testRequest.getPassword(), checkRequest.getPassword());
    }

    @Test
    public void testDefaultConstructor() {
        LoginRequest request = new LoginRequest();

        assertNotNull(request);
    }
}
