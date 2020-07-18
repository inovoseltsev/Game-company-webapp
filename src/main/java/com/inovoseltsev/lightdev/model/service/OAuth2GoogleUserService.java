package com.inovoseltsev.lightdev.model.service;

import com.inovoseltsev.lightdev.model.entity.OAuth2GoogleUser;

import java.util.List;

public interface OAuth2GoogleUserService {
    void create(OAuth2GoogleUser user);

    void update(OAuth2GoogleUser user);

    void delete(OAuth2GoogleUser user);

    OAuth2GoogleUser findById(String id);

    OAuth2GoogleUser findByEmail(String email);

    List<OAuth2GoogleUser> findAll();
}

