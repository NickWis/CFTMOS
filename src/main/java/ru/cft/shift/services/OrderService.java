package ru.cft.shift.services;

import ru.cft.shift.model.dto.OrderDto;

public interface OrderService {
    OrderDto getOrderById(Long orderId);

    OrderDto addOrder(OrderDto orderDto, Long serviceId);
}
