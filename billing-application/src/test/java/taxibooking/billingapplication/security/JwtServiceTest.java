package taxibooking.billingapplication.security;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import taxibooking.billingapplication.model.User;

@SpringBootTest
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private User user;

    @Mock
    private UserDetails userDetails;

    private String token;

}

