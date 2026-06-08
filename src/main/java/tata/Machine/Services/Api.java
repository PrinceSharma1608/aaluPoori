package tata.Machine.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tata.Machine.DTO.*;
import tata.Machine.Repository.*;
import tata.Machine.entity.*;
import tata.Machine.Services.*;
import java.util.*;
@RestController
@RequestMapping("/fetch")
public class Api {
    @Autowired
    private UsersService usersService;
    @Autowired
    private machineRepo machineRepo;
    @Autowired
    private maintenancelogRepo mlrp;
    @Autowired
    private areaRepo areaRepo;
    @Autowired
    private MachinesService msc;

    @GetMapping("/users")
    public List<usersDTO> FetchAllUsers() {
        return usersService.fetchAllUsers();
    }
    @GetMapping("/role")
    public List<usersDTO> fetchUsersByRole(@RequestParam users.UserRole role)
    {
        return usersService.fetchUsersByRole(role);
    }
    @GetMapping("/allMachine")
    public List<Machines> fetchAllMachines()
    {
        return machineRepo.findAll();
    }
    @GetMapping("/logs")
    public List<MaintenanceLogs> retrieveLogs()
    {
        return mlrp.findAll();
    }
@PostMapping("/a-mMap")
    public boolean amMapping(@RequestBody  List<MachineMappingDTO> mappings )
{
    return msc.AreaMachineMap(mappings);
}
}