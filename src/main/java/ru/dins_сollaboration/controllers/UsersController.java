package ru.dins_сollaboration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dins_сollaboration.dto.UserDto;
import ru.dins_сollaboration.mapper.UserDtoMapper;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.models.Role;
import ru.dins_сollaboration.models.State;
import ru.dins_сollaboration.models.User;
import ru.dins_сollaboration.repositories.PhonesRepository;
import ru.dins_сollaboration.repositories.UsersRepository;
import ru.dins_сollaboration.services.UsersService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0.1")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @PreAuthorize("hasAuthority('ADMIN')")// get All Users (ADMIN)
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(usersService.getUsers());
    }


    @PreAuthorize("hasAuthority('ADMIN')")// create new User (ADMIN)
    @PostMapping("/users")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {// without checking on identity phone number
        UserDto savedUser = usersService.saveUser(userDto);
        if (savedUser == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(savedUser);
    }




    @PreAuthorize("hasAuthority('ADMIN')")// get User by Id (ADMIN)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        UserDto user = usersService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }


    @PreAuthorize("hasAuthority('ADMIN')")// delete User by Id (ADMIN)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long userId) {
        if (usersService.getUser(userId) != null) {
            usersService.deleteUser(userId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PreAuthorize("hasAuthority('ADMIN')")// update User (ADMIN)
    @PutMapping("/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return  ResponseEntity.ok(usersService.updateUser(userDto));
    }


    @PreAuthorize("hasAuthority('ADMIN')")// get Users by name(path of name) (ADMIN) (find only one user)
    @GetMapping("/users/search")// в однотипном GET-запросе возможны различные PathVariable (!но НЕ @Param !!)
    public ResponseEntity<UserDto> getUsersByPathName(@Param("namepath") String namepath) {
        UserDto user = usersService.getUsersByPathName(namepath);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
