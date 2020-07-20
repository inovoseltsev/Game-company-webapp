package com.inovoseltsev.lightdev.service;

import com.inovoseltsev.lightdev.domain.AppUser;

import java.util.List;


public interface AppUserService {
    void create(AppUser user);

    void update(AppUser user);

    void delete(AppUser user);

    AppUser findById(Long id);

    AppUser findByLogin(String login);

    AppUser findByEmail(String email);

    List<AppUser> findAll();
}
