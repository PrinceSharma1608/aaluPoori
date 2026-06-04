package tata.Machine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.Machines;
import tata.Machine.entity.MaintenanceLogs;

public interface maintenancelogRepo extends JpaRepository<MaintenanceLogs,Integer>{

}
