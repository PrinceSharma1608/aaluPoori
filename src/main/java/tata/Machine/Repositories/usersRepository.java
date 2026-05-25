package tata.Machine.Repositories;

import tata.Machine.entity.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository
        extends JpaRepository<users, String> {
}