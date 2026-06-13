package tata.Machine.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.MaintenanceDashboardDTO;
import tata.Machine.Repository.*;
import tata.Machine.entity.CurrentDailyMaintenanceStatus;
import tata.Machine.entity.TeamLeaderJhOwnerMapping;
import tata.Machine.entity.users;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final DailyStatusRepo dailyRepo;
    private final machineRepo machineRepo;
    private final userRepo userRepo;
    private final TeamLeaderJHOwnerMappingRepo mappingRepo;
    private final areaRepo areaRepo;

    private List<CurrentDailyMaintenanceStatus>
    getJhOwnerMachines(
            String userId)
    {
        return dailyRepo.findAll()
                .stream()
                .filter(x ->
                        x.getMachine()
                                .getJhOwner()
                                .getUserId()
                                .equals(userId))
                .toList();
    }


    private List<CurrentDailyMaintenanceStatus>
    getSupervisorMachines(
            String userId)
    {
        return dailyRepo.findAll()
                .stream()
                .filter(x ->
                        x.getMachine()
                                .getArea()
                                .getSupervisor()
                                .getUserId()
                                .equals(userId))
                .toList();
    }


    private List<CurrentDailyMaintenanceStatus>
    getTeamLeaderMachines(
            String teamLeaderId)
    {
        List<String> jhOwners =
                mappingRepo
                        .findByTeamLeaderId(teamLeaderId)
                        .stream()
                        .map(TeamLeaderJhOwnerMapping::getJhOwnerId)
                        .toList();

        return dailyRepo.findAll()
                .stream()
                .filter(x ->
                        jhOwners.contains(
                                x.getMachine()
                                        .getJhOwner()
                                        .getUserId()))
                .toList();
    }


    private MaintenanceDashboardDTO convert(
            CurrentDailyMaintenanceStatus s)
    {
        MaintenanceDashboardDTO dto =
                new MaintenanceDashboardDTO();

        dto.setMachineId(
                s.getMachine()
                        .getMachineId());

        dto.setMachineName(
                s.getMachine()
                        .getMachineName());

        dto.setAreaId(
                s.getMachine()
                        .getArea()
                        .getAreaId());

        dto.setAreaName(
                s.getMachine()
                        .getArea()
                        .getAreaName());

        dto.setJhOwnerId(
                s.getMachine()
                        .getJhOwner()
                        .getUserId());

        dto.setMaintenanceStatus(
                s.getMaintenanceStatus()
                        .name());

        dto.setAudited(
                s.getAudited());

        return dto;
    }
    public List<MaintenanceDashboardDTO> getDashboard(
            String userId,
            String status)
    {
        users user =
                userRepo.findById(userId)
                        .orElseThrow();

        List<DashboardProjection> data =
                machineRepo.getDashboard();

        Stream<DashboardProjection> stream =
                data.stream();

        switch (user.getUserRole())
        {
            case LINE_INCHARGE:
                break;

            case SUPERVISOR:

                stream = stream.filter(x ->
                        areaRepo.findById(
                                        x.getAreaId())
                                .orElseThrow()
                                .getSupervisor()
                                .getUserId()
                                .equals(userId));
                break;

            case TEAM_LEADER:

                List<String> jhOwners =
                        mappingRepo
                                .findByTeamLeaderId(userId)
                                .stream()
                                .map(TeamLeaderJhOwnerMapping::getJhOwnerId)
                                .toList();

                stream = stream.filter(x ->
                        jhOwners.contains(
                                x.getJhOwnerId()));
                break;

            case JH_OWNER:

                stream = stream.filter(x ->
                        x.getJhOwnerId()
                                .equals(userId));
                break;

            default:
                throw new RuntimeException(
                        "Invalid Role");
        }

        if (!status.equalsIgnoreCase("ALL"))
        {
            stream = stream.filter(x ->
                    x.getMaintenanceStatus()
                            .equalsIgnoreCase(status));
        }

        return stream.map(x -> {

            MaintenanceDashboardDTO dto =
                    new MaintenanceDashboardDTO();

            dto.setMachineId(x.getMachineId());
            dto.setMachineName(x.getMachineName());
            dto.setAreaId(x.getAreaId());
            dto.setAreaName(x.getAreaName());
            dto.setJhOwnerId(x.getJhOwnerId());
            dto.setMaintenanceStatus(x.getMaintenanceStatus());

            return dto;

        }).toList();
    }
}