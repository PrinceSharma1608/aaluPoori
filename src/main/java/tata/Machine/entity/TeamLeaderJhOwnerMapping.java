package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;
import tata.Machine.entity.users;

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
    private users jhOwner;

    @ManyToOne
    @JoinColumn(name = "teamleader_id", nullable = false)
    private users teamLeader;
}