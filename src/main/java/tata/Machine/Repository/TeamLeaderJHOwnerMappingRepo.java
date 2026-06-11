package tata.Machine.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.TeamLeaderJhOwnerMapping;

import java.util.List;

public interface TeamLeaderJHOwnerMappingRepo
        extends JpaRepository<TeamLeaderJhOwnerMapping,String> {
    List<TeamLeaderJhOwnerMapping>
    findByTeamLeaderId(String teamLeaderId);

}