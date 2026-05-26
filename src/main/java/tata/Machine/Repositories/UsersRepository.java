package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
}