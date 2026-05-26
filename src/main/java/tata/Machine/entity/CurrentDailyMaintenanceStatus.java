package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;
import tata.Machine.entity.Machines;

import java.sql.Timestamp;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "current_daily_maintenance_status")
public class CurrentDailyMaintenanceStatus {

    public enum MaintenanceStatus {
        PENDING,
        COMPLETED,
        MISSED
    }

    @Id
    @OneToOne
    @JoinColumn(name = "machine_id")
    private Machines machine;

    @Column(name = "maintenance_date", nullable = false)
    private Date maintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_status", nullable = false)
    private MaintenanceStatus maintenanceStatus;

    @Column(name = "audited", nullable = false)
    private Boolean audited = false;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}