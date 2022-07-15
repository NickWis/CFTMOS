package ru.cft.shift.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.model.ClientService;

import java.util.List;

public interface ClientServiceRepository extends JpaRepository<ClientService, Long> {
    List<ClientService> findAllByClientId(Long userId);
}
