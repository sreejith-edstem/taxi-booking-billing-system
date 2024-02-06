package taxibooking.billingapplication.response;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.response.SignUpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void testCheckConstructor() {
        SignUpResponse testResponse = new SignUpResponse(1L,"joe","joe@gmail.com","Joe@123");
        SignUpResponse checkResponse = SignUpResponse.builder()
                .id(1L)
                .name("joe")
                .email("joe@gmail.com")
                .password("Joe@123")
                .build();

        assertEquals(testResponse.getName(), checkResponse.getName());
        assertEquals(testResponse.getEmail(), checkResponse.getEmail());
        assertEquals(testResponse.getPassword(),checkResponse.getPassword());
    }
    @Test
    public void testDefaultConstructor() {
        SignUpResponse response = new SignUpResponse();

        assertNotNull(response);
    }
}
