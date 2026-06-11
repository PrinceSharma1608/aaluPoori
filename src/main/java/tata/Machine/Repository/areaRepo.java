package tata.Machine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tata.Machine.entity.Areas;
import tata.Machine.entity.Machines;

import java.util.Optional;


@Component
@Repository
public interface areaRepo extends JpaRepository<Areas,String>
{
    Optional<Areas>
    findBySupervisor_UserId(String userId);

}