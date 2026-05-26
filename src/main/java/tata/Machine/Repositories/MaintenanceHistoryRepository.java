package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.MaintenanceHistory;
import tata.Machine.entity.MaintenanceHistory.MaintenanceStatus;

import java.sql.Date;
import java.util.List;

public interface MaintenanceHistoryRepository
        extends JpaRepository<MaintenanceHistory, Integer> {

    List<MaintenanceHistory>
    findByMachine_MachineId(String machineId);

    List<MaintenanceHistory>
    findByMaintenanceDate(Date maintenanceDate);

    List<MaintenanceHistory>
    findByMaintenanceStatus(MaintenanceStatus maintenanceStatus);
}