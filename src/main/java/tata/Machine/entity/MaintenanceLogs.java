package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance_logs")
public class MaintenanceLogs {

    public enum OverallStatus {
        GOOD,
        WARNING,
        CRITICAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machines machine;

    @ManyToOne
    @JoinColumn(name = "performed_by", nullable = false)
    private Users performedBy;

    @Column(name = "maintenance_date", nullable = false)
    private Timestamp maintenanceDate;

    @Column(name = "checklist", nullable = false, columnDefinition = "jsonb")
    private String checklist;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "overall_status", nullable = false)
    private OverallStatus overallStatus;
}