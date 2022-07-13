package ru.cft.shift.services;

import ru.cft.shift.model.dto.ClientServiceDto;

import java.util.List;

public interface TaskService {
    ClientServiceDto getClientServiceById(Long serviceId);

    ClientServiceDto addClientService(ClientServiceDto clientServiceDto);

    List<ClientServiceDto> getServicesByUser(Long userId);
}
