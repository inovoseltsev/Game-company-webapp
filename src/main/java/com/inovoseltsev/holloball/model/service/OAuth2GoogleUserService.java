package com.inovoseltsev.holloball.model.service;

import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;

import java.util.List;

public interface OAuth2GoogleUserService {
    void create(OAuth2GoogleUser user);

    void update(OAuth2GoogleUser user);

    void delete(OAuth2GoogleUser user);

    OAuth2GoogleUser findByEmail(String email);

    List<OAuth2GoogleUser> findAll();
}

