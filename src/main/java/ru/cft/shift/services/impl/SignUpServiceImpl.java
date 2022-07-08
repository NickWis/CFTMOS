package ru.cft.shift.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserResponse;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.services.SignUpService;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Override
    public UserResponse signUp(SignUpForm signUpForm) {
         if (((signUpForm.getFirstName()).length()<2)||((signUpForm.getFirstName()).matches(".*\\d+.*"))||
                ((signUpForm.getLastName()).length()<2)||((signUpForm.getLastName()).matches(".*\\d+.*"))||
                ((signUpForm.getMiddleName()).length()<2)||((signUpForm.getMiddleName()).matches(".*\\d+.*"))||
                ((signUpForm.getPassword()).length()<6)) {
            throw new IllegalArgumentException();
        }
        User user = User.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .middleName(signUpForm.getMiddleName())
                .password(signUpForm.getPassword())
                .build();

        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .build();

        return userResponse;

    }
}
