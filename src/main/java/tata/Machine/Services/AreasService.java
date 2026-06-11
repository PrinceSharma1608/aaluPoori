package tata.Machine.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.SupervisorAreaMappingDTO;
import tata.Machine.Repository.*;
import tata.Machine.entity.*;
import java.util.List;
@Service
class AreasService
{
    @Autowired
    private  areaRepo areaRepo;
    @Autowired
    private  userRepo userRepo;
    public boolean areaSupervisorMap(List<SupervisorAreaMappingDTO> mappings)
    {
        for (SupervisorAreaMappingDTO dto : mappings) {
            Areas area = areaRepo.findById(dto.getAreaId()).orElseThrow();
            users supervisor = userRepo.findById(dto.getSupervisorId()).orElseThrow();
            area.setSupervisor(supervisor);
            areaRepo.save(area);
        }
        return true;
    }
}