package com.inovoseltsev.lightdev.model.service.impl;

import com.inovoseltsev.lightdev.model.entity.AppUser;
import com.inovoseltsev.lightdev.model.repository.AppUserRepository;
import com.inovoseltsev.lightdev.model.role.Role;
import com.inovoseltsev.lightdev.model.service.AppUserService;
import com.inovoseltsev.lightdev.model.state.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppUserServiceImplTest {

    @Autowired
    private AppUserService appUserService;

    @MockBean
    private AppUserRepository appUserRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    private static AppUser initialUser;

    @BeforeAll
    public static void setUp() {
        initialUser = new AppUser("John", "Jackson", "johnJackson", "1234",
                "johnJackson@gmail.com", Role.USER, State.ACTIVE);
        initialUser.setUserId(0L);
    }

    @Test
    void create() {
        Mockito.doReturn("4321")
                .when(passwordEncoder)
                .encode("1234");
        appUserService.create(initialUser);
        Mockito.verify(passwordEncoder, Mockito.times(1))
                .encode("1234");
        Mockito.verify(appUserRepository, Mockito.times(1)).save(initialUser);
        assertEquals("4321", initialUser.getPassword());
    }

    @Test
    void update() {
        appUserService.update(initialUser);
        Mockito.verify(appUserRepository, Mockito.times(1)).save(initialUser);
    }

    @Test
    void delete() {
        appUserService.delete(initialUser);
        Mockito.verify(appUserRepository, Mockito.times(1)).delete(initialUser);
    }

    @Test
    void findById() {
        Long userId = 0L;
        Mockito.doReturn(Optional.of(initialUser))
                .when(appUserRepository)
                .findById(userId);
        AppUser foundUser = appUserService.findById(userId);
        Mockito.verify(appUserRepository, Mockito.times(1)).findById(userId);
        assertEquals(initialUser.getLogin(), foundUser.getLogin());

        userId = 1L;
        Mockito.doReturn(Optional.empty())
                .when(appUserRepository)
                .findById(userId);
        foundUser = appUserService.findById(userId);
        assertNull(foundUser);
    }

    @Test
    void findByLogin() {

    }

    @Test
    void findByEmail() {
    }

    @Test
    void findAll() {
    }
}