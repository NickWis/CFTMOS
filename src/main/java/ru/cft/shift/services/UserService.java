package ru.cft.shift.services;

import ru.cft.shift.model.User;

public interface UserService {
    User getUserById(Long userId);
}
