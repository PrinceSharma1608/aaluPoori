package tata.Machine.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tata.Machine.entity.Users;

@Getter
@AllArgsConstructor
public class loginResponse {
    private String token;
    private String role;
    private String sessionId;

    public loginResponse(String token, Users.UserRole userRole, String sessionId) {
    }
}