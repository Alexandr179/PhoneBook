package ru.dins_сollaboration.dto;

import lombok.*;
import ru.dins_сollaboration.models.Role;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String password;

    private String email;

    private Set<Role> roles;

    private String number;
}
