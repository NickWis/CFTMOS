package ru.cft.shift.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserDto;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.services.SignUpService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .middleName(signUpForm.getMiddleName())
                .password(signUpForm.getPassword())
                .build();

        userRepository.save(user);

        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .build();

        return userDto;

    }
}
