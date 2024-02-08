package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.request.UpdateAccountRequest;
import taxibooking.billingapplication.contract.response.LoginResponse;
import taxibooking.billingapplication.contract.response.SignUpResponse;
import taxibooking.billingapplication.contract.response.UpdateAccountResponse;
import taxibooking.billingapplication.exception.InvalidLoginException;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.repository.UserRepository;
import taxibooking.billingapplication.security.JwtService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private UserService userService = new UserService(null, null, null, null);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        userRepository = Mockito.mock(UserRepository.class);
        modelMapper = Mockito.mock(ModelMapper.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        jwtService = Mockito.mock(JwtService.class);
        userService = new UserService(userRepository,modelMapper,passwordEncoder,jwtService);
    }
    @Test
    void testSignUp() {

        SignUpRequest signupRequest = new SignUpRequest("Sreejith", "sree@Gmail.com", "Sree@258");
        User user =
                User.builder()
                        .name(signupRequest.getName())
                        .email(signupRequest.getEmail())
                        .password(passwordEncoder.encode(signupRequest.getPassword()))
                        .build();

        SignUpResponse expectedResponse =
                SignUpResponse.builder().email(user.getEmail()).name(user.getName()).build();

        when(userRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, SignUpResponse.class)).thenReturn(expectedResponse);

        SignUpResponse actualResponse = userService.signUp(signupRequest);

        assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void testAuthenticate(){
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        String jwtToken = "jwtToken";

        LoginRequest request = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();
        User user = User.builder()
                .password(encodedPassword)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        LoginResponse response = userService.authenticate(request);

        assertEquals(jwtToken, response.getToken());
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
        verify(jwtService, times(1)).generateToken(user);
    }
    @Test
    public void testAuthenticate_InvalidLogin() {
        String email = "test@example.com";
        String password = "password";

        LoginRequest request = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(InvalidLoginException.class, () -> userService.authenticate(request));
        verify(userRepository, times(1)).findByEmail(email);
    }
    @Test
    public void testAuthenticate_BadCredentials() {
        String email = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";

        LoginRequest request = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        User user = User.builder()
                .password(encodedPassword)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> userService.authenticate(request));
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }
    @Test
    public void testUpdateBalance() {
        Long id = 1L;
        User user = new User();
        UpdateAccountRequest request = new UpdateAccountRequest(50.5);
        UpdateAccountResponse expectedResponse = new UpdateAccountResponse(50.5);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UpdateAccountResponse.class))).thenReturn(expectedResponse);

        UpdateAccountResponse actualResponse = userService.updateBalance(id, request);

        assertEquals(expectedResponse, actualResponse);
    }
}
