package taxibooking.billingapplication.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Test
    public void testCheckConstructor() {
        User testUser = new User(1L,"joe","joe@gmail.com","Joe@123",4561);
        User checkUser = User.builder()
                .id(1L)
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
        User testUser = new User();

        assertNotNull(testUser);
    }
}
