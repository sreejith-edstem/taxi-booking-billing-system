package taxibooking.billingapplication.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taxibooking.billingapplication.contract.request.LoginRequest;
import taxibooking.billingapplication.contract.request.SignUpRequest;
import taxibooking.billingapplication.contract.request.UpdateAccountRequest;
import taxibooking.billingapplication.contract.response.LoginResponse;
import taxibooking.billingapplication.contract.response.SignUpResponse;
import taxibooking.billingapplication.contract.response.UpdateAccountResponse;
import taxibooking.billingapplication.model.User;
import taxibooking.billingapplication.repository.UserRepository;
import taxibooking.billingapplication.security.JwtService;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public SignUpResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Invalid Signup");
        }
        User user =
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
        user = userRepository.save(user);
        return modelMapper.map(user, SignUpResponse.class);
    }
    public LoginResponse authenticate(LoginRequest request) {
        User user = (User) userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String jwtToken = jwtService.generateToken(user);

        return LoginResponse.builder().token(jwtToken).build();
    }
    public UpdateAccountResponse updateBalance(long id, UpdateAccountRequest request){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        user = User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .accountBalance(user.getAccountBalance() + request.getAccountBalance())
                .build();
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UpdateAccountResponse.class);
    }
}
