package tata.Machine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tata.Machine.entity.users;

import java.util.List;
import java.util.Optional;
@Component
@Repository
public interface userRepo extends JpaRepository<users, String> {
    List<users> findByUserRole(
            users.UserRole userRole
    );
    Optional<users> findByUserId(String userId);

    Optional<users> findByUserName(String userName);

}