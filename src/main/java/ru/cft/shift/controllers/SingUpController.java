package ru.cft.shift.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserDto;
import ru.cft.shift.services.SignUpService;

@RestController
@RequestMapping("api/signUp")
@RequiredArgsConstructor
@Tag(name = "SingUpController", description = "Контроллер регистрации пользователя")
public class SingUpController {

    private final SignUpService signUpService;

    @Operation(summary = "Регистрация")
    @PostMapping()
    ResponseEntity<UserDto> signUp(@Parameter(description = "Данные о пользователе при регистрации") @RequestBody SignUpForm signUpForm){
        return ResponseEntity.ok().body(signUpService.signUp(signUpForm));
    }
}
