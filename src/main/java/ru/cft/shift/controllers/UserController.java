package ru.cft.shift.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.UserResponse;
import ru.cft.shift.services.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{user-id}")
    ResponseEntity<User> getUserById(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @GetMapping("/all")
    ResponseEntity<UserResponse> getAllUsers(){
        return ResponseEntity.ok().body(UserResponse.builder()
                .data(userService.getAllUsers())
                .build());
    }
}
