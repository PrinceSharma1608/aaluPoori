package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "machines")
public class Machines {

    public enum MachineStatus {
        ACTIVE,
        INACTIVE
    }

    @Id
    @Column(name = "machine_id", length = 50)
    private String machineId;

    @Column(name = "machine_name", nullable = false, length = 1000)
    private String machineName;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Areas area;

    @OneToOne
    @JoinColumn(name = "jhowner_id")

    private Users jhOwner;

    @Column(name = "subarea", length = 2000)
    private String subarea;

    @Column(name = "maintenance_frequency_days", nullable = false)
    private Integer maintenanceFrequencyDays;

    @Column(name = "delay_count", nullable = false)
    private Integer delayCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "machine_status", nullable = false)
    private MachineStatus machineStatus;
}