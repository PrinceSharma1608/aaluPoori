package tata.Machine.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String userId;
    private String userName;
    private String role;
}