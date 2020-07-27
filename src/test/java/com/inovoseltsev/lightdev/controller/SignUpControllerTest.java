package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.domain.AppUser;
import com.inovoseltsev.lightdev.repository.AppUserRepository;
import com.inovoseltsev.lightdev.repository.OAuth2GoogleUserRepository;
import com.inovoseltsev.lightdev.service.AppUserService;
import com.inovoseltsev.lightdev.validator.UserValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


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

    private static final MultiValueMap<String, String> userParameters =
            new LinkedMultiValueMap<>();

    @BeforeAll
    public static void setUp() {
        userParameters.add("firstName", "John");
        userParameters.add("lastName", "Johnson");
        userParameters.add("login", "johnJohnson");
        userParameters.add("repeatedPassword", "1234");
        userParameters.add("email", "johnJohnson@gmail.com");
    }

    @Test
    void displaySignUpPage() throws Exception {
        mockMvc.perform(get("/sign-up"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"));
    }

    @Test
    void createUserCorrect() throws Exception {
        AppUser createdUser = new AppUser();
        Mockito.when(userValidator.build(userParameters.toSingleValueMap()))
                .thenReturn(createdUser);
        mockMvc.perform(post("/sign-up").params(userParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("sign-in"));
        Mockito.verify(userValidator, Mockito.times(1))
                .build(userParameters.toSingleValueMap());
        Mockito.verify(userService, Mockito.times(1)).create(createdUser);
    }

    @Test
    void createUserFail() throws Exception {
        Mockito.when(userValidator.build(userParameters.toSingleValueMap()))
                .thenReturn(null);
        Mockito.when(userValidator.getErrorMessage())
                .thenReturn("Error");
        mockMvc.perform(post("/sign-up").params(userParameters))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("sign-up"))
                .andExpect(content().contentType(MediaType.TEXT_HTML_VALUE +
                        ";charset=UTF-8"));
        Mockito.verify(userValidator, Mockito.times(1))
                .build(userParameters.toSingleValueMap());
        Mockito.verify(userValidator, Mockito.times(1)).getErrorMessage();
    }
}