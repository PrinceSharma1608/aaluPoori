package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.AuditLogs;
import tata.Machine.entity.AuditLogs.AuditResult;

import java.util.List;

public interface AuditLogsRepository
        extends JpaRepository<AuditLogs, Integer> {

    List<AuditLogs>
    findByMachine_MachineId(String machineId);

    List<AuditLogs>
    findByAuditResult(AuditResult auditResult);
}