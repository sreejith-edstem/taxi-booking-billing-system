package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.SignUpRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void testCheckConstructor() {
        SignUpRequest testRequest = new SignUpRequest("joe","joe@gmail.com","Joe@123");
        SignUpRequest checkRequest = SignUpRequest.builder()
                .name("joe")
                .email("joe@gmail.com")
                .password("Joe@123")
                .build();

        assertEquals(testRequest.getName(), checkRequest.getName());
        assertEquals(testRequest.getEmail(), checkRequest.getEmail());
        assertEquals(testRequest.getPassword(), checkRequest.getPassword());
    }
    @Test
    public void testDefaultConstructor() {
        SignUpRequest request = new SignUpRequest();

        assertNotNull(request);
    }
}
