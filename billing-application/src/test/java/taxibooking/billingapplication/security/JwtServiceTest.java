package taxibooking.billingapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;
import taxibooking.billingapplication.model.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static taxibooking.billingapplication.security.JwtService.JWT_TOKEN_VALIDITY;

public class JwtServiceTest {


    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private Claims claims;

    private JwtService jwtService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
    }
    @Test
    public void testGenerateToken() {
        // Arrange
        User userDetails = User.builder()
                .id(1L)
                .email("test@gmail.com")
                .build();
//        userDetails.setId(1L);
//        userDetails.setEmail("test@example.com");

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.getId());

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String expectedToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(key)
                .compact();

        // Act
        String actualToken = jwtService.generateToken(userDetails);

        // Assert
        assertEquals(expectedToken, actualToken);
    }


}
