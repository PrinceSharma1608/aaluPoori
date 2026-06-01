package tata.Machine.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.usersDTO;
import tata.Machine.Repository.userRepo;
import tata.Machine.entity.users;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsersService
{
    @Autowired
    private userRepo userRepo;
    public List<usersDTO> fetchAllUsers() {
    List<users> usersList = userRepo.findAll();
        List<usersDTO> dtoList = new ArrayList<>();
    for(users user : usersList)
    {
    usersDTO dto = new usersDTO();
    dto.setUserId(user.getUserId());
    dto.setUserName(user.getUserName());
    dto.setUserRole(user.getUserRole().toString());
    dtoList.add(dto);
    }
    return dtoList;
    }
    //for specific users
    public List<usersDTO> fetchUsersByRole(users.UserRole role)
    {
        List<users> usersList = userRepo.findByUserRole(role);
        List<usersDTO> dtoList = new ArrayList<>();
        for(users user : usersList)
        {
            usersDTO dto = new usersDTO();
            dto.setUserId(user.getUserId());
            dto.setUserName(user.getUserName());
            dto.setUserRole(user.getUserRole().toString());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
