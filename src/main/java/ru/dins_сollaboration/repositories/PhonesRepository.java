package ru.dins_сollaboration.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNullApi;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.models.State;

import java.util.List;
import java.util.Optional;

public interface PhonesRepository extends JpaRepository<PhoneNote, Long> {

    List<PhoneNote> findAllByState(State state);

    List<PhoneNote> findAllByPhUser_Id(Long userId);

    Optional<PhoneNote> findById(Long id);

    List<PhoneNote> findAllByPhoneNumber(String number);
}
