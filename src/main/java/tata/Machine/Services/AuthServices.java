package tata.Machine.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.login;
import tata.Machine.DTO.loginResponse;
import tata.Machine.Repositories.usersRepository;
import tata.Machine.entity.users;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final usersRepository userRepository;

    private final JwtService jwtService;

    public loginResponse login(
           login request
    ) {

        users user = userRepository
                .findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        )
                );

        if (!user.getUserPassword()
                .equals(request.getPassword())) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getUserId(),
                        user.getUserRole()
                );

        String sessionId =
                jwtService.extractSessionId(token);

        return new loginResponse(
                token,
                user.getUserRole(),
                sessionId
        );
    }
}
