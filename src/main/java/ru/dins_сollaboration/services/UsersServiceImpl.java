package ru.dins_сollaboration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dins_сollaboration.dto.UserDto;
import ru.dins_сollaboration.mapper.UserDtoMapper;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.models.State;
import ru.dins_сollaboration.models.User;
import ru.dins_сollaboration.repositories.PhonesRepository;
import ru.dins_сollaboration.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private UserDtoMapper userDtoMapper;


    @Override
    public List<UserDto> getUsers() {
        return usersRepository.findAll().stream()
                .map(user -> userDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }


    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userDtoMapper.toEntity(userDto);
        if (usersRepository.findFirstByEmail(user.getEmail()).isPresent()) {
            return null;
        }
        User savedUser = usersRepository.save(user);
        phonesRepository.save(
                PhoneNote.builder()
                        .createdTime(LocalDateTime.now())
                        .phoneNumber(userDto.getNumber())
                        .state(State.ALLOWED)
                        .text("\"-- empty notation " + userDto.getFirstName() + " --\"")
                        .phUser(savedUser)
                        .build());
        return userDtoMapper.toDto(savedUser);
    }


    @Override
    public UserDto getUser(Long userId) {
        Optional<User> user = usersRepository.findById(userId);//to use .getOne -is a BAD! practise
        return user.map(value -> userDtoMapper.toDto(value)).orElseGet(() -> null);
    }


    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }


    @Override
    public UserDto updateUser(UserDto userDto) {
        String phoneNumber = userDto.getNumber();
        User user = userDtoMapper.toEntity(userDto);
        User savedUser = usersRepository.save(user);
        phonesRepository.findAllByPhUser_Id(savedUser.getId()).stream()
                .map(phoneNote -> {
                    phoneNote.setPhoneNumber(phoneNumber);
                    return phonesRepository.save(phoneNote);
                })
                .collect(Collectors.toList());// not .close()!
        return userDtoMapper.toDto(savedUser);
    }


    @Override
    public UserDto getUsersByPathName(String namepath) {
        Optional<User> user = usersRepository.findFirstByFirNameContaining(namepath);
        return user.map(value -> userDtoMapper.toDto(value)).orElseGet(() -> null);
    }
}
