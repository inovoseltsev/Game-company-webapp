package com.inovoseltsev.holloball.model.repository;

import com.inovoseltsev.holloball.model.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserByLogin(String login);
    AppUser findAppUserByEmail(String email);
}