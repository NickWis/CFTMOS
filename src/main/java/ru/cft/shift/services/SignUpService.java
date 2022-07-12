package ru.cft.shift.services;

import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpForm signUpForm);
}
