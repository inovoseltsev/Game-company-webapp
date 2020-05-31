package com.inovoseltsev.holloball.model.service;

import com.inovoseltsev.holloball.model.entity.AppUser;

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
