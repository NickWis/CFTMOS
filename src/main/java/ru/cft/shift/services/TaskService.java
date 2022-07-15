package ru.cft.shift.services;
import ru.cft.shift.model.ClientService;
import ru.cft.shift.model.dto.ClientServiceDto;

import java.util.List;

public interface TaskService {
    ClientServiceDto getClientServiceById(Long serviceId);

    ClientServiceDto addClientService(ClientServiceDto clientServiceDto);

    List<ClientServiceDto> getServicesByUser(Long userId);
    
    //New
    ClientServiceDto updateService(ClientServiceDto clientServiceDto, Long serviceId);
     //New
     void deleteById(Long serviceId);
}
