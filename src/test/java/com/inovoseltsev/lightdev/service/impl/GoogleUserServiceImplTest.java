package com.inovoseltsev.lightdev.service.impl;


import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.state.State;
import com.inovoseltsev.lightdev.repository.GoogleUserRepository;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class GoogleUserServiceImplTest {

    @Autowired
    private GoogleUserService googleUserService;

    @MockBean
    private GoogleUserRepository googleUserRepository;

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    private static GoogleUser initialUser;

    @BeforeAll
    public static void setUp() {
        initialUser = new GoogleUser("1", "Johnson", "John",
                "johnson", "johnJackson@gmail.com", "en");
        initialUser.setState(State.ACTIVE);
    }

    @Test
    void create() {
        googleUserService.create(initialUser);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .save(initialUser);
    }

    @Test
    void update() {
        googleUserService.update(initialUser);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .save(initialUser);
    }

    @Test
    void delete() {
        googleUserService.delete(initialUser);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .delete(initialUser);
    }

    @Test
    void findById() {
        String userId = "1";
        Mockito.when(googleUserRepository.findById(userId))
                .thenReturn(Optional.of(initialUser));
        GoogleUser foundUser = googleUserService.findById(userId);
        assertEquals(foundUser, initialUser);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .findById(userId);

        String falseUserId = "nothing";
        Mockito.when(googleUserRepository.findById(falseUserId))
                .thenReturn(Optional.empty());
        foundUser = googleUserService.findById(falseUserId);
        assertNull(foundUser);
    }

    @Test
    void findByEmail() {
        String userEmail = "johnJackson@gmail.com";
        Mockito.when(googleUserRepository.findAuthUserByEmail(userEmail))
                .thenReturn(initialUser);
        GoogleUser foundUser = googleUserService.findByEmail(userEmail);
        assertEquals(foundUser, initialUser);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .findAuthUserByEmail(userEmail);

        String falseUserEmail = "nothing";
        Mockito.when(googleUserRepository.findAuthUserByEmail(falseUserEmail))
                .thenReturn(null);
        foundUser = googleUserService.findByEmail(falseUserEmail);
        assertNull(foundUser);
    }

    @Test
    void findAll() {
        List<GoogleUser> users = Collections.singletonList(initialUser);
        Mockito.when(googleUserRepository.findAll())
                .thenReturn(users);
        List<GoogleUser> foundUsers = googleUserService.findAll();
        assertEquals(foundUsers, users);
        Mockito.verify(googleUserRepository, Mockito.times(1))
                .findAll();
    }
}