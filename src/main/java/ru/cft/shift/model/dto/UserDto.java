package ru.cft.shift.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.cft.shift.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String middleName;

    public static UserDto from(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .middleName(user.getMiddleName())
                .build();
    }

    public static List<UserDto> from(List<User> users){
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
