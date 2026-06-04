package tata.Machine.Services;


import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.AreaMachineMappingDTO;
import tata.Machine.DTO.machineDTO;
import tata.Machine.Repository.areaRepo;
import tata.Machine.Repository.machineRepo;
import tata.Machine.entity.Areas;
import tata.Machine.entity.Machines;

import java.util.*;

@Service
class  MachinesService {
    @Autowired
    private  machineRepo machineRepo;
    @Autowired
    private  areaRepo areaRepo;

    public List<machineDTO> fetchAllMachines() {
        List<Machines> machinesList = machineRepo.findAll();

        List<machineDTO> dtoList = new ArrayList<>();

        for (Machines machine : machinesList) {
            machineDTO dto = new machineDTO();
            dto.setMachineId(machine.getMachineId());
            dto.setMachineName(machine.getMachineName());
            dto.setArea(machine.getArea().getAreaName());
            dto.setJhOwner(machine.getJhOwner().getUserName());
            dto.setSubarea(machine.getSubarea());
            dto.setMaintenanceFrequencyDays(machine.getMaintenanceFrequencyDays());
            dto.setDelayCount(machine.getDelayCount());
            dto.setMachineStatus(machine.getMachineStatus().toString());
            dtoList.add(dto);
        }

        return dtoList;
    }

    public boolean AreaMachineMap( List<AreaMachineMappingDTO> mappings) {
        List<Machines> machinesToUpdate = new ArrayList<>();
        for (AreaMachineMappingDTO dto : mappings) {
            Machines machine = machineRepo.findById(dto.getMachineId()).orElseThrow();
            Areas area = areaRepo.findById(dto.getAreaId()).orElseThrow();
            machine.setArea(area);
            machinesToUpdate.add(machine);
        }
        machineRepo.saveAll(machinesToUpdate);
        return true;
    }
}