package ru.cft.shift.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
