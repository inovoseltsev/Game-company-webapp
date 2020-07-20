package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.model.service.AppUserService;
import com.inovoseltsev.lightdev.model.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SignUpController.class)
class SignUpControllerTest {

    @Autowired
    private SignUpController signUpController;

    @MockBean
    private AppUserService userService;

    @MockBean
    private UserValidator userValidator;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void displaySignUpPage() {

    }

    @Test
    void createUser() {
    }
}