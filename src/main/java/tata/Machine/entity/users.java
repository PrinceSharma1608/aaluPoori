package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class users {

    public enum UserRole {
        LINE_INCHARGE,
        SUPERVISOR,
        TEAM_LEADER,
        JH_OWNER
    }

    @Id
    @Column(name = "user_id", length = 9)
    private String userId;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "user_password", nullable = false, length = 100)
    private String userPassword;
}