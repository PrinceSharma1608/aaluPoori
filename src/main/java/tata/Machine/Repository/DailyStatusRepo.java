package tata.Machine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tata.Machine.entity.CurrentDailyMaintenanceStatus;

import java.util.List;

@Repository
public interface DailyStatusRepo extends JpaRepository<CurrentDailyMaintenanceStatus, String> {
}