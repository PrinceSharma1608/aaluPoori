package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.CurrentDailyMaintenanceStatus;
import tata.Machine.entity.CurrentDailyMaintenanceStatus.MaintenanceStatus;

import java.sql.Date;
import java.util.List;

public interface CurrentDailyMaintenanceStatusRepository
        extends JpaRepository<CurrentDailyMaintenanceStatus, String> {

    List<CurrentDailyMaintenanceStatus>
    findByMaintenanceStatus(MaintenanceStatus maintenanceStatus);

    List<CurrentDailyMaintenanceStatus>
    findByMaintenanceDate(Date maintenanceDate);

    List<CurrentDailyMaintenanceStatus>
    findByAudited(Boolean audited);
}