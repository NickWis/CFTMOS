package ru.cft.shift.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.model.dto.OrderDto;
import ru.cft.shift.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
@Tag(name = "OrderController", description = "Контроллер заказов")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Получить заказ по id")
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderDto> getOrderById (@Parameter(description = "ID заказа") @PathVariable("order-id") Long orderId){
        return ResponseEntity.ok().body(orderService.getOrderById(orderId));
    }

    @Operation(summary = "Добавить услугу по id")
    @PostMapping("/service/{service-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@Parameter(description = "Данные о заказе которые будут обновлены") @RequestBody OrderDto orderDto, @Parameter(description = "ID услуги, которую нужно заказать") @PathVariable("service-id") Long serviceId){
        return orderService.addOrder(orderDto, serviceId);
    }
}
