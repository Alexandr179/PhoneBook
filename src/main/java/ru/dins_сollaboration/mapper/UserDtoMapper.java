package ru.dins_сollaboration.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.dins_сollaboration.dto.UserDto;
import ru.dins_сollaboration.models.User;
import ru.dins_сollaboration.repositories.PhonesRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Objects;

@Component
public class UserDtoMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PhonesRepository phonesRepository;

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(User.class, UserDto.class)
                .addMappings(m -> m.skip(UserDto::setNumber)).setPostConverter(toDtoConverter());
        // (пропускаем поле, конвертируем его ниже в ручную..)

        mapper.createTypeMap(UserDto.class, User.class)
        // (UserDto.phoneNumber и User.List<PhoneNote> - это поля не совпадающие по типу -> конвертации не произойдет)
                .addMappings(m -> m.skip(User::setHashPassword)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(User::setTokens)).setPostConverter(toEntityConverter());
    }

    // ---------------------- toDto --------------------------------
    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }

    private Converter<User, UserDto> toDtoConverter() {
        return context -> {
            User source = context.getSource();
            UserDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(User source, UserDto destination) {
        destination.setNumber(Objects.requireNonNull(phonesRepository.findAllByPhUser_Id(source.getId()).stream()
                .findAny().orElse(null)).getPhoneNumber());
    }

    // ---------------------- toEntity --------------------------------
    public User toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    private Converter<UserDto, User> toEntityConverter() {
        return context -> {
            UserDto source = context.getSource();
            User destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(UserDto source, User destination) {
        destination.setHashPassword(passwordEncoder.encode(source.getPassword()));
        destination.setTokens(Arrays.asList(source.getFirstName() + "_token", source.getFirstName() + "_token2"));
    }
}