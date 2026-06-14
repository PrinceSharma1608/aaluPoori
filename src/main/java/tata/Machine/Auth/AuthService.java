package tata.Machine.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.LoginRequest;
import tata.Machine.DTO.LoginResponse;
import tata.Machine.Repository.userRepo;
import tata.Machine.entity.users;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final userRepo userRepo;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request)
    {
        users user =
                userRepo.findById(
                                request.getUserId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid User ID"));

        if (!user.getUserPassword()
                .equals(request.getPassword()))
        {
            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                jwtService.generateToken(
                        user.getUserId(),
                        user.getUserRole().name());

        return new LoginResponse(
                token,
                user.getUserId(),
                user.getUserName(),
                user.getUserRole().name()
        );
    }
}