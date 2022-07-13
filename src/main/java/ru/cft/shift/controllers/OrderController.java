package ru.cft.shift.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.model.dto.OrderDto;
import ru.cft.shift.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderDto> getOrderById (@PathVariable("order-id") Long orderId){
        return ResponseEntity.ok().body(orderService.getOrderById(orderId));
    }

    @PostMapping("/service/{service-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@RequestBody OrderDto orderDto, @PathVariable("service-id") Long serviceId){
        return orderService.addOrder(orderDto, serviceId);
    }
}
