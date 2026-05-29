package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance_history")
public class MaintenanceHistory {

    public enum MaintenanceStatus {
        PENDING,
        COMPLETED,
        MISSED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machines machine;

    @Column(name = "maintenance_date", nullable = false)
    private Date maintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_status", nullable = false)
    private MaintenanceStatus maintenanceStatus;

    @Column(name = "audited", nullable = false)
    private Boolean audited;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Users updatedBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}