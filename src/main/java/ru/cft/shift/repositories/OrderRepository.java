package ru.cft.shift.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cft.shift.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
