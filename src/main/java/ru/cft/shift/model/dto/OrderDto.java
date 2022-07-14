package ru.cft.shift.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cft.shift.model.Order;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    @Schema(description = "ID пользователя", example = "1")
    private Long userId;

    public static OrderDto from(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders){
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
