package ru.cft.shift.services;

import ru.cft.shift.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();
}
