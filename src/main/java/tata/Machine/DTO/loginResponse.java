package tata.Machine.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tata.Machine.entity.users;

@Getter
@AllArgsConstructor
public class loginResponse {
    private String token;
    private String role;
    private String sessionId;

    public loginResponse(String token, users.UserRole userRole, String sessionId) {
    }
}