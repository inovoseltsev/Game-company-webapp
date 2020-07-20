package com.inovoseltsev.lightdev.repository;

import com.inovoseltsev.lightdev.domain.OAuth2GoogleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2GoogleUserRepository extends CrudRepository<OAuth2GoogleUser, String> {
    OAuth2GoogleUser findAuthUserByEmail(String email);
}
