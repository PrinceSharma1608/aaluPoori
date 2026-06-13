package tata.Machine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tata.Machine.entity.Machines;
import tata.Machine.entity.users;

import java.util.List;
import java.util.Optional;
@Component
@Repository
public interface machineRepo extends JpaRepository<Machines,String>
{
    List<Machines> findByJhOwner_UserId(String userId);
    List<Machines> findByArea_AreaId(String areaId);


    @Query(value = """
            SELECT
    m.machine_id as machineId,
    m.machine_name as machineName,
    a.area_id as areaId,
    a.area_name as areaName,
    m.jhowner_id as jhOwnerId,
    COALESCE(c.maintenance_status,'PENDING')
        as maintenanceStatus
FROM machines m
LEFT JOIN current_daily_maintenance_status c
       ON m.machine_id = c.machine_id
LEFT JOIN areas a
       ON m.area_id = a.area_id
""", nativeQuery = true)
    List<DashboardProjection> getDashboard();

}
