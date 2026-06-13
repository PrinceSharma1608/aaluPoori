package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name="current_daily_maintenance_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentDailyMaintenanceStatus {

    public enum MaintenanceStatus
    {
        PENDING,
        COMPLETED,
        MISSED
    }

    @Id
    @Column(name="machine_id")
    private String machineId;

    @OneToOne
    @JoinColumn(
            name="machine_id",
            referencedColumnName="machine_id",
            insertable=false,
            updatable=false
    )
    private Machines machine;

    @Column(name="maintenance_date")
    private LocalDate maintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name="maintenance_status")
    private MaintenanceStatus maintenanceStatus;

    @Column(name="audited")
    private Boolean audited;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}