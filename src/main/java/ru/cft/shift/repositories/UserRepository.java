package ru.cft.shift.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String lastName);
    Optional<User> findByToken(String token);
}
