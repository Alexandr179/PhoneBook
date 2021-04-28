package ru.dins_сollaboration.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dins_сollaboration.dto.PhoneDto;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.repositories.UsersRepository;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PhoneDtoMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsersRepository usersRepository;


    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(PhoneNote.class, PhoneDto.class);

        mapper.createTypeMap(PhoneDto.class, PhoneNote.class)
                .addMappings(m -> m.skip(PhoneNote::setPhUser)).setPostConverter(toEntityConverter());
    }

    // ---------------------- toDto --------------------------------
    public PhoneDto toDto(PhoneNote entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, PhoneDto.class);
    }

    private Converter<PhoneNote, PhoneDto> toDtoConverter() {
        return context -> {
            PhoneNote source = context.getSource();
            PhoneDto destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    private void mapSpecificFields(PhoneNote source, PhoneDto destination) {
        //
    }


    // ---------------------- toEntity --------------------------------
    public PhoneNote toEntity(PhoneDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, PhoneNote.class);
    }

    private Converter<PhoneDto, PhoneNote> toEntityConverter() {
        return context -> {
            PhoneDto source = context.getSource();
            PhoneNote destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(PhoneDto source, PhoneNote destination) {
        destination.setPhUser(usersRepository.findById(source.getUserId()).orElse(null));
    }
}