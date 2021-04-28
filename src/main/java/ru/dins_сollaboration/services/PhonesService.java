package ru.dins_сollaboration.services;

import ru.dins_сollaboration.dto.PhoneDto;
import java.util.List;

public interface PhonesService {

    List<PhoneDto> getAllNotes(Long userId);

    PhoneDto savePhone(PhoneDto phoneDto);

    PhoneDto getNotePhone(Long id);

    PhoneDto updatePhone(PhoneDto phoneDto);

    void deleteNotePhone(Long id);

    List<PhoneDto> getNoteByPhoneNumber(String number);
}
