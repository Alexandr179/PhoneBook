package ru.dins_сollaboration.services;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.dins_сollaboration.utils.TestDataUtil;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @SpringBootTest
 * - Spring-test пройдется по packages (директория повторяет c этой целью дир.приложения)
 * - система восстановит context приложения, найдет все config-и
 * @AutoConfigureMockMvc
 * - Spring создает и подменяет классы директории слоя MVC..
 *
 * Rest spring boot test: https://asbnotebook.com/2020/05/28/spring-boot-rest-controller-junit-test-example/
 */


@SpringBootTest
@AutoConfigureMockMvc
class PhonesServiceImplTest {

    @Autowired
    private PhonesService phonesService;

    @Autowired
    private TestDataUtil testDataUtil;

    @BeforeEach// not - @BeforeTestClass
    public void setUp() {
        testDataUtil.initializeData();
    }


    @Test
    public void getAllNotes() throws Exception {
        // - integration-test
        assertEquals(phonesService.getAllNotes((long) 2).size(), 3);
        assertThat(phonesService).isNotNull();//controller - is pulled from Context
    }

    // ... ... ..

    @Test
    public void savePhone() {
    }

    @Test
    public void getNotePhone() {
    }

    @Test
    public void updatePhone() {
    }

    @Test
    public void deleteNotePhone() {
    }

    @Test
    public void getNoteByPhoneNumber() {
    }
}