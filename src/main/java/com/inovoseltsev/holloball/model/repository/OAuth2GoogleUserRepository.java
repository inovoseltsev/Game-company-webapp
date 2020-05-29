package com.inovoseltsev.holloball.model.repository;

import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2GoogleUserRepository extends CrudRepository<OAuth2GoogleUser, String> {
    OAuth2GoogleUser findAuthUserByEmail(String email);
}
