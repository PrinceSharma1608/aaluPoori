package tata.Machine.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.TeamLeaderJhOwnerMapping;

public interface TeamLeaderJHOwnerMappingRepo
        extends JpaRepository<TeamLeaderJhOwnerMapping,String> {
}