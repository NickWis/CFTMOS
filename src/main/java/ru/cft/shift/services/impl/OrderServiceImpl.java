package ru.cft.shift.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.model.ClientService;
import ru.cft.shift.model.Order;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.OrderDto;
import ru.cft.shift.repositories.ClientServiceRepository;
import ru.cft.shift.repositories.OrderRepository;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.services.OrderService;

import static ru.cft.shift.model.dto.OrderDto.from;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ClientServiceRepository serviceRepository;
    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.getReferenceById(orderId);
        return from(order);
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto, Long serviceId) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with this id doesn't exist"));
       if(user.getClientServices().stream().anyMatch(clientService -> clientService.getId().equals(serviceId))){
           throw new IllegalArgumentException("CAN'T ADD ORDER TO YOURSELF!");
       }
        ClientService service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service with this id doesn't exist"));

        Order order = Order.builder()
                .clientService(service)
                .user(user)
                .build();
        return from(orderRepository.save(order));
    }
}
