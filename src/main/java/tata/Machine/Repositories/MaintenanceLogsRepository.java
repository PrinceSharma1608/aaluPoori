package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.MaintenanceLogs;

import java.util.List;

public interface MaintenanceLogsRepository
        extends JpaRepository<MaintenanceLogs, Integer> {

    List<MaintenanceLogs> findByMachine_MachineId(String machineId);
}