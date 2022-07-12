package ru.cft.shift.controllers;

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
public class ClientServiceController {

    private final TaskService taskService;

    @GetMapping("/{service-id}")
    public ResponseEntity<ClientServiceDto> getClientServiceById(@PathVariable("service-id") Long serviceId){
        return ResponseEntity.ok().body(taskService.getClientServiceById(serviceId));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientServiceDto addClientService(@RequestBody ClientServiceDto clientServiceDto){
        return taskService.addClientService(clientServiceDto);
    }
}
