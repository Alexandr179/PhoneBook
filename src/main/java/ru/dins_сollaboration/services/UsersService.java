package ru.dins_сollaboration.services;

import ru.dins_сollaboration.dto.UserDto;
import java.util.List;

public interface UsersService {

    List<UserDto> getUsers();

    UserDto saveUser(UserDto userDto);

    UserDto getUser(Long userId);

    void deleteUser(Long userId);

    UserDto updateUser(UserDto userDto);

    UserDto getUsersByPathName(String namepath);
}