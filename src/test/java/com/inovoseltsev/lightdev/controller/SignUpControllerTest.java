package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.repository.AppUserRepository;
import com.inovoseltsev.lightdev.repository.OAuth2GoogleUserRepository;
import com.inovoseltsev.lightdev.service.AppUserService;
import com.inovoseltsev.lightdev.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(SignUpController.class)
class SignUpControllerTest {

    @Autowired
    private SignUpController signUpController;

    @MockBean
    private AppUserService userService;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private UserValidator userValidator;

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    @MockBean
    private OAuth2GoogleUserRepository googleUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void displaySignUpPage() {
    }

    @Test
    void createUser() {
    }
}