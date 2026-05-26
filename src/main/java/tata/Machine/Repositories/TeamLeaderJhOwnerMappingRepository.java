package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.TeamLeaderJhOwnerMapping;

public interface TeamLeaderJhOwnerMappingRepository
        extends JpaRepository<TeamLeaderJhOwnerMapping, String> {
}