package taxibooking.billingapplication.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void getPassword(){
        User user = User.builder()
                .password("Abv@123")
                .build();
        assertEquals(user.getPassword(),"Abv@123");
    }
    @Test
    void getEmail(){
        User user = User.builder()
                .email("sree54@gmail.com")
                .build();
        assertEquals(user.getEmail(),"sree54@gmail.com");
    }
    @Test
    void getName(){
        User user = User.builder()
                .name("Sreejith")
                .build();
        assertEquals(user.getName(),"Sreejith");
    }
    @Test
    void getAccountBalance(){
        User user = User.builder()
                .accountBalance(5689)
                .build();
        assertEquals(user.getAccountBalance(),5689);
    }
}
