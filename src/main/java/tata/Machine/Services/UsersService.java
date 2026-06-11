package tata.Machine.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tata.Machine.DTO.*;
import tata.Machine.Repository.*;
import tata.Machine.entity.*;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsersService
{
    @Autowired
    private TeamLeaderJHOwnerMappingRepo mappingRepo;

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


    public void mapTeamLeader(List<TeamLeaderMappingDTO> dtoList)
    {
        for(TeamLeaderMappingDTO dto : dtoList)
        {
            users teamLeader =
                    userRepo.findById(dto.getTeamLeaderId())
                            .orElseThrow();

            if(teamLeader.getUserRole() != users.UserRole.TEAM_LEADER)
            {
                throw new RuntimeException("Invalid Team Leader");
            }

            for(String jhOwnerId : dto.getJhOwnerIds())
            {
                users jhOwner =
                        userRepo.findById(jhOwnerId)
                                .orElseThrow();

                TeamLeaderJhOwnerMapping mapping =
                        new TeamLeaderJhOwnerMapping();

                mapping.setJhOwnerId(jhOwner.getUserId());

                mapping.setTeamLeaderId(teamLeader.getUserId());

                mappingRepo.save(mapping);
            }
        }
    }
}
