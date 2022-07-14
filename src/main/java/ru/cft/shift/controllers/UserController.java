package ru.cft.shift.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.model.dto.UserDto;
import ru.cft.shift.model.dto.UserResponse;
import ru.cft.shift.services.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "Контроллер получения данных пользователя")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получение пользователя по ID")
    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUserById(@Parameter(description = "ID пользователя") @PathVariable("user-id") Long userId){
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @Operation(summary = "Получение всех пользователей")
    @GetMapping("/all")
    ResponseEntity<UserResponse> getAllUsers(){
        return ResponseEntity.ok().body(UserResponse.builder()
                .data(userService.getAllUsers())
                .build());
    }
}
