package taxibooking.billingapplication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;
import taxibooking.billingapplication.model.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
//    @Test
//    public void testGenerateToken() {
//        // Arrange
//        long userId = 1L;
//        String userEmail = "test@example.com";
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//        when(user.getId()).thenReturn(userId);
//        when(user.getEmail()).thenReturn(userEmail);
//        when(jwtService.getSignInKey()).thenReturn(key);
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", userId);
//
//        String expectedToken = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userEmail)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//
//        // Act
//        String actualToken = jwtService.generateToken(user);
//
//        // Assert
//        assertEquals(expectedToken, actualToken);
//    }
}

