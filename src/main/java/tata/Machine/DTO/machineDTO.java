package tata.Machine.DTO;

import lombok.*;
@Getter
@Setter
public class machineDTO {
    private String machineId;
    private String machineName;
    private String area;
    private String jhOwner;
    private String subarea;
    private Integer maintenanceFrequencyDays;
    private Integer delayCount = 0;
    private String  machineStatus;
}

