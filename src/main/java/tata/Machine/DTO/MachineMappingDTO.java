package tata.Machine.DTO;
import jakarta.persistence.*;
import lombok.*;
import tata.Machine.entity.Areas;
import tata.Machine.entity.Machines;
import tata.Machine.entity.users;

@Getter
@Setter
public class MachineMappingDTO {
    public enum MachineStatus {
        ACTIVE,
        INACTIVE
    }

    private String machineId;
    private String areaId;
   private MachineStatus status;
    private String jhOwnerId;
    private String subarea;
    private Integer maintenanceFrequencyDays;
}
