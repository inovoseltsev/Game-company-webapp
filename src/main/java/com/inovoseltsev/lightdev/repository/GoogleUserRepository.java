package com.inovoseltsev.lightdev.repository;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleUserRepository extends CrudRepository<GoogleUser, Long> {
    GoogleUser findAuthUserByEmail(String email);
}
