package ru.dins_сollaboration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dins_сollaboration.dto.PhoneDto;
import ru.dins_сollaboration.mapper.PhoneDtoMapper;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.repositories.PhonesRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhonesServiceImpl implements PhonesService {

    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private PhoneDtoMapper phoneDtoMapper;


    @Override
    public List<PhoneDto> getAllNotes(Long userId) {
        List<PhoneNote> phoneNotes = phonesRepository.findAllByPhUser_Id(userId);
        return phoneNotes.stream()
                .map(phoneNote -> phoneDtoMapper.toDto(phoneNote))
                .collect(Collectors.toList());
    }


    @Override
    public PhoneDto savePhone(PhoneDto phoneDto) {
        PhoneNote phoneNote = phoneDtoMapper.toEntity(phoneDto);
        phoneNote.setCreatedTime(LocalDateTime.now());
        PhoneNote savedPhoneNote = phonesRepository.save(phoneNote);
        return phoneDtoMapper.toDto(savedPhoneNote);
    }


    @Override
    public PhoneDto getNotePhone(Long id) {
        Optional<PhoneNote> phoneNote = phonesRepository.findById(id);
        return phoneNote.map(note -> phoneDtoMapper.toDto(note)).orElseGet(() -> null);
    }


    @Override
    public PhoneDto updatePhone(PhoneDto phoneDto) {
        PhoneNote phoneNote = phoneDtoMapper.toEntity(phoneDto);
        phoneNote.setCreatedTime(LocalDateTime.now());
        PhoneNote savedPhoneNote = phonesRepository.save(phoneNote);
        return phoneDtoMapper.toDto(savedPhoneNote);
    }


    @Override
    public void deleteNotePhone(Long id) {
        phonesRepository.deleteById(id);
    }


    @Override
    public List<PhoneDto> getNoteByPhoneNumber(String number) {
        List<PhoneNote> phoneNotes = phonesRepository.findAllByPhoneNumber(number);
        if (phoneNotes.isEmpty()) {
            return new ArrayList<>();
        }
        return phoneNotes.stream()
                .map(phoneNote -> phoneDtoMapper.toDto(phoneNote))
                .collect(Collectors.toList());
    }
}
