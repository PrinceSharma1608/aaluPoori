package tata.Machine.Services;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tata.Machine.DTO.login;
import tata.Machine.DTO.loginResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authService;

    @PostMapping("/login")
    public loginResponse login(
            @RequestBody login request
    ) {

        return authService.login(request);
    }
}