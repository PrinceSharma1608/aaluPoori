package tata.Machine.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.Machine.entity.users;

public interface usersRepository
        extends JpaRepository<users, String> {
}