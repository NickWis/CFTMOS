package ru.cft.shift.services;

import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.UserDto;

import java.util.List;

public interface UserService {
    User getUserById(Long userId);

    List<UserDto> getAllUsers();
}
