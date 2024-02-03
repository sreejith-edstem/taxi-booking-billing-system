package taxibooking.billingapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.response.SignUpResponse;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.repository.UserRepository;
import taxibooking.billingapplication.security.JwtService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

        SignUpRequest signupRequest = new SignUpRequest("vignesh", "vig@Gmail.com", "vig@123");
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
}
