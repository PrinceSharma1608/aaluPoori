package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;
import tata.Machine.entity.Users;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teamleader_jhowner_mapping")
public class TeamLeaderJhOwnerMapping {

    @Id
    @OneToOne
    @JoinColumn(name = "jhowner_id")
    private Users jhOwner;

    @ManyToOne
    @JoinColumn(name = "teamleader_id", nullable = false)
    private Users teamLeader;
}