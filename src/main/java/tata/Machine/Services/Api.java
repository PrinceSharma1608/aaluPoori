package tata.Machine.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tata.Machine.Auth.AuthService;
import tata.Machine.DTO.*;
import tata.Machine.Repository.*;
import tata.Machine.entity.*;
import java.util.*;
@RestController
@RequestMapping("/fetch")
public class Api {
    @Autowired
    private UsersService usersService;
    @Autowired
    private machineRepo machineRepo;
    @Autowired
    private DailyStatusRepo dailyRepo;
    @Autowired
    private areaRepo areaRepo;
    @Autowired
    private MachinesService msc;
    @Autowired
    private MaintenanceService masc;
    @Autowired
    private AreasService asc;

    @GetMapping("/users")
    public List<usersDTO> FetchAllUsers() {
        return usersService.fetchAllUsers();
    }

    @GetMapping("/role")
    public List<usersDTO> fetchUsersByRole(@RequestParam users.UserRole role) {
        return usersService.fetchUsersByRole(role);
    }
    /*@GetMapping("/machineid")
    public List<usersDTO> fetchMachinebyId(@RequestParam String machineid) {
        return usersService.fetchUsersByRole(machineid);
    }*/

    @GetMapping("/allMachine")
    public List<machineDTO> fetchAllMachines() {
        return msc.fetchAllMachines();
    }

    @GetMapping("/dashboard")
    public ResponseEntity<List<MaintenanceDashboardDTO>> dashboard(@RequestParam String userId, @RequestParam String status)
    {
        return ResponseEntity.ok(masc.getDashboard(userId, status));
    }
   /* @GetMapping("/logs")
    public List<MaintenanceLogs> retrieveLogs() {
        return mlrp.findAll();
    }*/

    @RestController
    @RequestMapping("/map")
    class mapping {
        @PutMapping("/mMap")
        public boolean amMapping(@RequestBody List<MachineMappingDTO> mappings) {
            return msc.MachineMap(mappings);
        }
        @PutMapping("/asMap")
        public boolean areaSupervisorMap(@RequestBody List<SupervisorAreaMappingDTO> mappings) {

            return asc.areaSupervisorMap(mappings);
        }
        @PostMapping("/teamleader")
        public ResponseEntity<String> mapTeamLeaders(@RequestBody List<TeamLeaderMappingDTO> dto) {
            usersService.mapTeamLeader(dto);

            return ResponseEntity.ok("Mapping Successful");
        }
    }
    @RestController
    @RequestMapping("/auth")
    @RequiredArgsConstructor
    public class AuthController {

        private final AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(
                @RequestBody LoginRequest request)
        {
            return ResponseEntity.ok(
                    authService.login(request));
        }
    }
}