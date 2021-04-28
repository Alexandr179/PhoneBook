package ru.dins_сollaboration.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.dins_сollaboration.services.PhonesService;
import ru.dins_сollaboration.utils.TestDataUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PhonesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @BeforeEach// not - @BeforeTestClass
    public void setUp() {
        testDataUtil.initializeData();
    }


    @Test
    public void getAllNotes() throws Exception {
        // - module-test (url: "/api/v0.1/phones/users/2")
        mockMvc.perform(get("/api/v0.1/phones/users/2").header("Authentication", "user2_token"))
                .andDo(print())// to console..
                .andExpect(status().isOk())// wrapper at Assert
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
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