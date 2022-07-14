package ru.cft.shift.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.model.dto.ClientServiceDto;
import ru.cft.shift.model.dto.ClientServiceResponse;
import ru.cft.shift.services.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
@Tag(name = "ClientServiceController", description = "Контроллер услуг")
public class ClientServiceController {

    private final TaskService taskService;

    @Operation(summary = "Получить услугу по id")
    @GetMapping("/{service-id}")
    public ResponseEntity<ClientServiceDto> getClientServiceById(@Parameter(description = "id услуги, которую хотим получить") @PathVariable("service-id") Long serviceId){
        return ResponseEntity.ok().body(taskService.getClientServiceById(serviceId));
    }

    @Operation(summary = "Получить все услуги по id пользователя")
    @GetMapping("/user/{user-id}")
    public ResponseEntity<ClientServiceResponse> getServicesByUser(@Parameter(description = "id пользователя, у которого хотим получить все услуги") @PathVariable("user-id") Long userId){
        return ResponseEntity.ok().body(ClientServiceResponse.builder()
                .data(taskService.getServicesByUser(userId))
                .build());
    }

    @Operation(summary = "Удалить услугу по id")
    @DeleteMapping("/delete/{service-id}")
    public String deleteById(@Parameter(description = "id услуги, которую хотим удалить") @PathVariable("service-id") Long serviceId){
        taskService.deleteById(serviceId);
        return "Deleted Successfully";
    }

    @Operation(summary = "Обновить данные в услуге")
    @PutMapping("/update/{service-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ClientServiceDto updateService(@Parameter(description = "Данные об услуге которые будут обновлены") @RequestBody ClientServiceDto clientServiceDto, @Parameter(description = "id услуги, которую хотим обновить") @PathVariable("service-id") Long serviceId) {
        return taskService.updateService(clientServiceDto, serviceId);
    }

    @Operation(summary = "Добавить услугу")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientServiceDto addClientService(@Parameter(description = "Данные об услуге которые будут добавлены") @RequestBody ClientServiceDto clientServiceDto){
        return taskService.addClientService(clientServiceDto);
    }
}
