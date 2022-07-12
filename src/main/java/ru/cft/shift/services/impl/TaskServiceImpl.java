package ru.cft.shift.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.model.ClientService;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.ClientServiceDto;
import ru.cft.shift.repositories.ClientServiceRepository;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.services.TaskService;

import java.util.Optional;

import static ru.cft.shift.model.dto.ClientServiceDto.from;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final ClientServiceRepository clientServiceRepository;
    private final UserRepository userRepository;

    @Override
    public ClientServiceDto getClientServiceById(Long serviceId) {
        ClientService clientService = clientServiceRepository.getReferenceById(serviceId);
        return from(clientService);
    }

    @Override
    public ClientServiceDto addClientService(ClientServiceDto clientServiceDto) {
        Optional<User> user = userRepository.findById(clientServiceDto.getUserId());
        ClientService clientService = ClientService.builder()
                .description(clientServiceDto.getDescription())
                .date(clientServiceDto.getDate())
                .city(clientServiceDto.getCity())
                .build();
        user.ifPresent(clientService::setClient);
        return from(clientServiceRepository.save(clientService));
    }
}
