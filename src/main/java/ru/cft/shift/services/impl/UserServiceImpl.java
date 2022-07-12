package ru.cft.shift.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.UserDto;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.services.UserService;

import java.util.List;

import static ru.cft.shift.model.dto.UserDto.from;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Пользователя с таким айди не существует"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return from(users);
    }
}
