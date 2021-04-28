package ru.dins_сollaboration.controllers;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dins_сollaboration.dto.PhoneDto;
import ru.dins_сollaboration.mapper.PhoneDtoMapper;
import ru.dins_сollaboration.mapper.UserDtoMapper;
import ru.dins_сollaboration.models.PhoneNote;
import ru.dins_сollaboration.models.User;
import ru.dins_сollaboration.repositories.PhonesRepository;
import ru.dins_сollaboration.repositories.UsersRepository;
import ru.dins_сollaboration.services.PhonesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0.1")
public class PhonesController {// ...generated Test's

    @Autowired
    private PhonesService phonesService;

    // Swagger-2 documentation examples:
    // https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/
    // see example on ..
    @ApiOperation(value = "Получение всех телефонных записей по userId (Authentication: user2_token, id=2)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "PoneNotes with phoneNumber are existing."),
            @ApiResponse(code = 404, message = "PoneNote with phoneNumber not found...")
    })
    @ApiImplicitParam(name = "Authentication", value = "token", required = true, dataType = "string", paramType = "header")

    @PreAuthorize("hasAuthority('USER')")// get all phoneNotes by userId (USER)
    @GetMapping("/phones/users/{id}")
    public ResponseEntity<List<PhoneDto>> getAllNotes(@ApiParam(value = "id PhoneDto", required = true)
                                                      @PathVariable("id") Long userId) {
        List<PhoneDto> notes = phonesService.getAllNotes(userId);
        if (!notes.isEmpty()) {
            return ResponseEntity.ok(notes);
        }
        return ResponseEntity.notFound().build();
    }


    @PreAuthorize("hasAuthority('USER')")// create new PhoneNote (USER)
    @PostMapping("/phones")
    public ResponseEntity<PhoneDto> savePhone(@RequestBody PhoneDto phoneDto) {
        return ResponseEntity.ok(phonesService.savePhone(phoneDto));
    }


    @PreAuthorize("hasAuthority('USER')")// get phoneNote by id (USER)
    @GetMapping("/phones/{id}")
    public ResponseEntity<PhoneDto> getNotePhone(@PathVariable("id") Long id) {
        PhoneDto notePhone = phonesService.getNotePhone(id);
        if (notePhone != null) {
            return ResponseEntity.ok(notePhone);
        }
        return ResponseEntity.notFound().build();
    }


    @PreAuthorize("hasAuthority('USER')")// update phoneNote (USER)
    @PutMapping("/phones")
    public ResponseEntity<PhoneDto> updatePhone(@RequestBody PhoneDto phoneDto) {
        return ResponseEntity.ok(phonesService.updatePhone(phoneDto));
    }


    @PreAuthorize("hasAuthority('USER')")// delete phoneNote by id (USER)
    @DeleteMapping("/phones/{id}")
    public ResponseEntity<Object> deleteNotePhone(@PathVariable("id") Long id) {
        if (phonesService.getNotePhone(id) != null) {
            phonesService.deleteNotePhone(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



    @PreAuthorize("hasAuthority('USER')")// get phoneNotes by phoneNumber (USER)
    @GetMapping("/phones")
    public ResponseEntity<List<PhoneDto>> getNoteByPhoneNumber(@Param("number") String number) {
        List<PhoneDto> phoneNotes = phonesService.getNoteByPhoneNumber(number);
        if (phoneNotes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(phoneNotes);
    }
}
