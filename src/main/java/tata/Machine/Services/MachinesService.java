package tata.Machine.Services;


import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.MachineMappingDTO;
import tata.Machine.DTO.machineDTO;
import tata.Machine.Repository.areaRepo;
import tata.Machine.Repository.machineRepo;
import tata.Machine.Repository.userRepo;
import tata.Machine.entity.Areas;
import tata.Machine.entity.Machines;
import tata.Machine.entity.users;

import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
class  MachinesService {
    @Autowired
    private  machineRepo machineRepo;
    @Autowired
    private  areaRepo areaRepo;
    @Autowired
    private userRepo userRepo;
    public List<machineDTO> fetchAllMachines() {
        List<Machines> machinesList = machineRepo.findAll();

        List<machineDTO> dtoList = new ArrayList<>();

        for (Machines machine : machinesList) {
            machineDTO dto = new machineDTO();
            dto.setMachineId(machine.getMachineId());
            dto.setMachineName(machine.getMachineName());
            dto.setArea(
                    machine.getArea() != null
                            ? machine.getArea().getAreaName()
                            : "Unassigned"
            );

            dto.setJhOwner(
                    machine.getJhOwner() != null
                            ? machine.getJhOwner().getUserName()
                            : "Not Assigned"
            );

            dto.setSubarea(machine.getSubarea());
            dto.setMaintenanceFrequencyDays(
                    machine.getMaintenanceFrequencyDays()
            );
            dto.setDelayCount(machine.getDelayCount());
            dto.setMachineStatus(
                    machine.getMachineStatus().toString()
            );
            dtoList.add(dto);
        }

        return dtoList;
    }

    public boolean MachineMap( List<MachineMappingDTO> mappings) {
        List<Machines> machinesToUpdate = new ArrayList<>();
        for (MachineMappingDTO dto : mappings) {
            Machines machine = machineRepo.findById(
                    dto.getMachineId()
            ).orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Machine not found with ID: " + id));

            if (dto.getAreaId() != null) {

                Areas area = areaRepo.findById(
                        dto.getAreaId()
                ).orElseThrow();

                machine.setArea(area);
            }

            if (dto.getJhOwnerId() != null) {

                users jhOwner = userRepo.findById(
                        dto.getJhOwnerId()
                ).orElseThrow();

                machine.setJhOwner(jhOwner);
            }

            if (dto.getSubarea() != null) {
                machine.setSubarea(dto.getSubarea());
            }

            if (dto.getMaintenanceFrequencyDays() != null) {
                machine.setMaintenanceFrequencyDays(
                        dto.getMaintenanceFrequencyDays()
                );
            }

            if (dto.getStatus() != null) {
                machine.setMachineStatus(
                        Machines.MachineStatus.valueOf(
                                dto.getStatus().name()
                        )
                );
            }
            machineRepo.save(machine);
        }
            return true;
        }
    }