package ru.dins_сollaboration.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.dins_сollaboration.models.*;
import ru.dins_сollaboration.repositories.PhonesRepository;
import ru.dins_сollaboration.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Component
public class TestDataUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhonesRepository phonesRepository;


    @Transactional
    public void initializeData() {

        User admin = User.builder()
                .firName("admin")
                .email("admin@gmail.com")
                .hashPassword(passwordEncoder.encode("qwerty"))
                .tokens(Arrays.asList("admin_token", "admin_token2"))//         simple security_rest_tokens
                .roles(Collections.singleton(Role.ADMIN))
                .build();

        User user1 = User.builder()
                .firName("user1")
                .email("user1@gmail.com")
                .hashPassword(passwordEncoder.encode("qwerty"))
                .tokens(Arrays.asList("user1_token", "user1_token2"))
                .roles(Collections.singleton(Role.USER))
                .build();

        User user2 = User.builder()
                .firName("user2")
                .email("user2@gmail.com")
                .hashPassword(passwordEncoder.encode("qwerty"))
                .tokens(Arrays.asList("user2_token", "user2_token2"))
                .roles(Collections.singleton(Role.USER))
                .build();

        User operator = User.builder()
                .firName("operator")
                .email("operator@gmail.com")
                .hashPassword(passwordEncoder.encode("qwerty"))
                .tokens(Arrays.asList("operator_token", "operator_token2"))
                .roles(Collections.singleton(Role.OPERATOR))
                .build();


        usersRepository.save(admin);
        usersRepository.save(user1);
        usersRepository.save(user2);
        usersRepository.save(operator);


        // phoneNumber at JPA and queries must have pattern: 89110347007 (! without '+' ' ' and other !)

        PhoneNote admin_note1 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("89110347007")
                .text("notification 1")
                .createdTime(LocalDateTime.now())// !necessarily by TIMESTAMP DEFAULT now() at DB
                .phUser(admin)
                .build();

        PhoneNote user1_note1 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("81111111111")
                .text("notification 1")
                .createdTime(LocalDateTime.now())
                .phUser(user1)
                .build();

        PhoneNote user1_note2 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("81111111111")
                .text("notification 2")
                .createdTime(LocalDateTime.now())
                .phUser(user1)
                .build();

        PhoneNote user1_note3 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("81111111111")
                .text("notification 3")
                .createdTime(LocalDateTime.now())
                .phUser(user1)
                .build();

        PhoneNote user2_note1 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("82222222222")
                .text("notification 1")
                .createdTime(LocalDateTime.now())
                .phUser(user2)
                .build();

        PhoneNote operator_note1 = PhoneNote.builder()
                .state(State.ALLOWED)
                .phoneNumber("80000000000")
                .text("notification 1")
                .createdTime(LocalDateTime.now())
                .phUser(operator)
                .build();

        phonesRepository.save(admin_note1);

        phonesRepository.save(user1_note1);
        phonesRepository.save(user1_note2);
        phonesRepository.save(user1_note3);

        phonesRepository.save(user2_note1);
        phonesRepository.save(operator_note1);
    }
}
