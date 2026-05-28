package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;
import tata.Machine.entity.Users;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas")
public class Areas {

    @Id
    @Column(name = "area_id", length = 10)
    private String areaId;

    @Column(name = "area_name", nullable = false, length = 100)
    private String areaName;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Users supervisor;
}