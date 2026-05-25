package tata.Machine.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class loginResponse {
    private String token;
    private String role;
    private String sessionId;
}