package tata.Machine.DTO;

import lombok.Data;
import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
public class TeamLeaderMappingDTO {

    private String teamLeaderId;

    private List<String> jhOwnerIds;
}