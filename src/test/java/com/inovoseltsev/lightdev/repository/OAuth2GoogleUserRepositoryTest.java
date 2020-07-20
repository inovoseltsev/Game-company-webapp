package com.inovoseltsev.lightdev.repository;

import com.inovoseltsev.lightdev.domain.OAuth2GoogleUser;
import com.inovoseltsev.lightdev.state.State;
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
class OAuth2GoogleUserRepositoryTest {

    @Autowired
    private OAuth2GoogleUserRepository oAuth2GoogleUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    private static OAuth2GoogleUser initialUser;

    private OAuth2GoogleUser foundUser;

    @BeforeAll
    public static void setInitialUser() {
        initialUser = new OAuth2GoogleUser("123123123", "Johnson", "John",
                "johnson", "johnJackson@gmail.com", "en", State.ACTIVE);
    }

    @Test
    void findAuthUserByEmail() {
        entityManager.persist(initialUser);
        entityManager.flush();
        foundUser = oAuth2GoogleUserRepository
                .findAuthUserByEmail("johnJackson@gmail.com");
        assertEquals(foundUser.getEmail(), initialUser.getEmail());
        foundUser = oAuth2GoogleUserRepository.findAuthUserByEmail("notExists");
        assertNull(foundUser);
    }
}