package tata.Machine.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.MaintenanceDashboardDTO;
import tata.Machine.Repository.TeamLeaderJHOwnerMappingRepo;
import tata.Machine.Repository.areaRepo;
import tata.Machine.Repository.machineRepo;
import tata.Machine.entity.*;
import tata.Machine.Repository.userRepo;

import java.util.ArrayList;
import java.util.List;
@Service
class MaintenanceService
{
    @Autowired
    public machineRepo machineRepo;
    @Autowired
    public areaRepo areaRepo;
    @Autowired
    public userRepo userRepo;
    @Autowired
    public TeamLeaderJHOwnerMappingRepo mappingRepo;
    private List<MaintenanceDashboardDTO>
    convertToDTO(
            List<Machines> machines)
    {
        List<MaintenanceDashboardDTO>
                dtoList =
                new ArrayList<>();

        for(Machines machine : machines)
        {
            MaintenanceDashboardDTO dto =
                    new MaintenanceDashboardDTO();

            dto.setMachineId(
                    machine.getMachineId());

            dto.setMachineName(
                    machine.getMachineName());

            dto.setAreaId(
                    machine.getArea().getAreaId());

            dto.setAreaName(
                    machine.getArea().getAreaName());

            dto.setJhOwnerId(
                    machine.getJhOwner().getUserId());

            dto.setMaintenanceStatus(
                    machine.getFlag());

            dto.setDelayCount(
                    machine.getDelayCount());

            dtoList.add(dto);
        }

        return dtoList;
    }
    private List<MaintenanceDashboardDTO>
    getJhOwnerMachines(String userId)
    {
        List<Machines> machines =
                machineRepo.findByJhOwner_UserId(
                        userId
                );

        return convertToDTO(machines);
    }
    private List<MaintenanceDashboardDTO>
    getSupervisorMachines(String userId)
    {
        Areas area =
                areaRepo.findBySupervisor_UserId(
                                userId
                        )
                        .orElseThrow();

        List<Machines> machines =
                machineRepo.findByArea_AreaId(
                        area.getAreaId()
                );

        return convertToDTO(machines);
    }
    private List<MaintenanceDashboardDTO>
    getTeamLeaderMachines(String userId)
    {
        List<TeamLeaderJhOwnerMapping>
                mappings =
                mappingRepo.findByTeamLeaderId(
                        userId
                );

        List<Machines> result =
                new ArrayList<>();

        for(TeamLeaderJhOwnerMapping m :
                mappings)
        {
            result.addAll(
                    machineRepo.findByJhOwner_UserId(
                            m.getJhOwnerId()
                    )
            );
        }

        return convertToDTO(result);
    }

    private List<MaintenanceDashboardDTO>
    getAllMachines()
    {
        return convertToDTO(
                machineRepo.findAll()
        );
    }



    public List<MaintenanceDashboardDTO>
    getDashboard(String userId, String status)
    {
        users user = userRepo.findById(userId).orElseThrow();

        switch(user.getUserRole())
        {
            case LINE_INCHARGE:
                return getAllMachines();

            case SUPERVISOR:
                return getSupervisorMachines(userId);

            case TEAM_LEADER:
                return getTeamLeaderMachines(userId);

            case JH_OWNER:
                return getJhOwnerMachines(userId);

            default:
                throw new RuntimeException(
                        "Invalid Role"
                );
        }
    }
}