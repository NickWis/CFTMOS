package ru.cft.shift.services;

import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserResponse;

public interface SignUpService {
    UserResponse signUp(SignUpForm signUpForm);
}
