package com.inovoseltsev.lightdev.repository;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.domain.role.Role;
import com.inovoseltsev.lightdev.domain.state.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    private static AppUser initialUser;

    private static AppUser foundUser;

    @BeforeAll
    public static void setInitialUser() {
        initialUser = new AppUser("John", "Jackson", "johnJackson", "1234",
                "johnJackson@gmail.com", Role.USER);
        initialUser.setState(State.ACTIVE);
    }

    @Test
    void findAppUserByLogin() {
        entityManager.persist(initialUser);
        entityManager.flush();
        foundUser = appUserRepository.findAppUserByLogin(initialUser.getLogin());
        assertEquals(initialUser.getLogin(), foundUser.getLogin());
        foundUser = appUserRepository.findAppUserByLogin("notExists");
        assertNull(foundUser);
    }

    @Test
    void findAppUserByEmail() {
        entityManager.merge(initialUser);
        entityManager.flush();
        foundUser = appUserRepository.findAppUserByEmail(initialUser.getEmail());
        assertEquals(foundUser.getEmail(), initialUser.getEmail());
        foundUser = appUserRepository.findAppUserByEmail("notExists");
        assertNull(foundUser);
    }
}