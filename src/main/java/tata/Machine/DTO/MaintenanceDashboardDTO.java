package tata.Machine.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDashboardDTO {

    private String machineId;
    private String machineName;
    private String areaId;
    private String areaName;
    private String jhOwnerId;
    private String maintenanceStatus;
    private Boolean audited;
}