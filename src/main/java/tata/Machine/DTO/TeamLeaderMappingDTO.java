package tata.Machine.DTO;

import lombok.Data;
import java.util.List;
@Data
public class TeamLeaderMappingDTO {

    private String teamLeaderId;

    private List<String> jhOwnerIds;
}