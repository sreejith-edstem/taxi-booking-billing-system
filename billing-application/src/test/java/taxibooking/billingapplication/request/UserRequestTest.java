package taxibooking.billingapplication.request;

import org.junit.jupiter.api.Test;
import taxibooking.billingapplication.contract.request.UserRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRequestTest {
    @Test
    void getPassword(){
        UserRequest request = UserRequest.builder()
                .password("Abv@123")
                .build();
        assertEquals(request.getPassword(),"Abv@123");
    }
    @Test
    void getEmail(){
        UserRequest request = UserRequest.builder()
                .email("sree54@gmail.com")
                .build();
        assertEquals(request.getEmail(),"sree54@gmail.com");
    }
    @Test
    void getName(){
        UserRequest request = UserRequest.builder()
                .name("Sreejith")
                .build();
        assertEquals(request.getName(),"Sreejith");
    }
    @Test
    void getAccountBalance(){
        UserRequest request = UserRequest.builder()
                .accountBalance(5689)
                .build();
        assertEquals(request.getAccountBalance(),5689);
    }
    @Test
    public void checkConstructor() {
        UserRequest testUser = new UserRequest("joe","joe@gmail.com","Joe@123",4561);
        UserRequest checkUser = UserRequest.builder()
                .name("joe")
                .email("joe@gmail.com")
                .password("Joe@123")
                .accountBalance(4561)
                .build();

        assertEquals(testUser.getName(), checkUser.getName());
        assertEquals(testUser.getEmail(), checkUser.getEmail());
        assertEquals(testUser.getPassword(), checkUser.getPassword());
    }

    @Test
    public void testDefaultConstructor() {
        UserRequest testUser = new UserRequest();

        assertNotNull(testUser);
    }
}
