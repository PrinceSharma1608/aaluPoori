package tata.Machine.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audit_logs")
public class AuditLogs {

    public enum MaintenanceStatus {
        PENDING,
        COMPLETED,
        MISSED
    }

    public enum AuditResult {
        PASS,
        FAIL,
        WARNING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer auditId;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    private Machines machine;

    @ManyToOne
    @JoinColumn(name = "audited_by", nullable = false)
    private Users auditedBy;

    @Column(name = "audit_date", nullable = false)
    private Timestamp auditDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "maintenance_status", nullable = false)
    private MaintenanceStatus maintenanceStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_result", nullable = false)
    private AuditResult auditResult;

    @Column(name = "findings")
    private String findings;

    @Column(name = "checklist", columnDefinition = "jsonb")
    private String checklist;
}