package com.inovoseltsev.lightdev.repository;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findAppUserByLogin(String login);
    AppUser findAppUserByEmail(String email);
}