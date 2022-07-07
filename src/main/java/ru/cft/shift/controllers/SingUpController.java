package ru.cft.shift.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.model.dto.SignUpForm;
import ru.cft.shift.model.dto.UserResponse;
import ru.cft.shift.services.SignUpService;

@RestController
@RequestMapping("api/signUp")
@RequiredArgsConstructor
public class SingUpController {

    private final SignUpService signUpService;

    @PostMapping()
    ResponseEntity<UserResponse> signUp(@RequestBody SignUpForm signUpForm){
        return ResponseEntity.ok().body(signUpService.signUp(signUpForm));
    }
}
