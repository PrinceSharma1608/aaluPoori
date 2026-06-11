package tata.Machine.DTO;


import lombok.*;

@Getter
@Setter
@Data
public class MaintenanceDashboardDTO {

    private String machineId;

    private String machineName;

    private String areaId;

    private String areaName;

    private String jhOwnerId;

    private String maintenanceStatus;

    private Integer delayCount;
}