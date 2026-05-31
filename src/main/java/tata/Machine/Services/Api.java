package tata.Machine.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tata.Machine.DTO.usersDTO;
import tata.Machine.Repository.userRepo;
import tata.Machine.entity.users;
import tata.Machine.Services.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fetch")
public class Api {
    @Autowired
    private UsersService usersService;
    @GetMapping("/users")
    public List<usersDTO> FetchAllUsers() {
        return usersService.fetchAllUsers();

    }
    @GetMapping("/role")
    public List<usersDTO> fetchUsersByRole(
            @RequestParam users.UserRole role)
    {
        return usersService
                .fetchUsersByRole(role);
    }
}