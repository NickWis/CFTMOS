package ru.cft.shift.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.model.ClientService;
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

    @GetMapping("/user/{user-id}")
    public ResponseEntity<ClientServiceResponse> getServicesByUser(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok().body(ClientServiceResponse.builder()
                .data(taskService.getServicesByUser(userId))
                .build());
    }
    
     //New
    @DeleteMapping("/delete/{service-id}")
    public String deleteById(@PathVariable("service-id") Long serviceId){
        taskService.deleteById(serviceId);
        return "Deleted Successfully";
    }
    //New
    @PutMapping("/update")
    public ClientService UpdateService(@RequestBody ClientServiceDto clientServiceDto, @PathVariable Long serviceId) {
        return taskService.UpdateService(clientServiceDto, serviceId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClientServiceDto addClientService(@RequestBody ClientServiceDto clientServiceDto){
        return taskService.addClientService(clientServiceDto);
    }
}
