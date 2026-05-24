package tata.Machine.Services;

import org.apache.catalina.User;
import tata.Machine.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.login;
import tata.Machine.DTO.loginResponse;
import tata.Machine.entity.users;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final users userRepository;

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
                io.jsonwebtoken.Jwts.parser()
                        .setSigningKey(
                                "THIS_IS_A_SECRET_KEY_FOR_JWT"
                        )
                        .parseClaimsJws(token)
                        .getBody()
                        .get(
                                "sessionId",
                                String.class
                        );

        return new loginResponse(
                token,
                user.getUserRole(),
                sessionId
        );
    }
}