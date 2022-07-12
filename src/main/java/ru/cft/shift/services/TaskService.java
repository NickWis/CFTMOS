package ru.cft.shift.services;

import ru.cft.shift.model.dto.ClientServiceDto;

public interface TaskService {
    ClientServiceDto getClientServiceById(Long serviceId);

    ClientServiceDto addClientService(ClientServiceDto clientServiceDto);
}
