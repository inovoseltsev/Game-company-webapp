package com.inovoseltsev.lightdev.service;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import java.util.List;

public interface GoogleUserService {
    void create(GoogleUser user);

    void update(GoogleUser user);

    void delete(GoogleUser user);

    GoogleUser findById(Long id);

    GoogleUser findByEmail(String email);

    List<GoogleUser> findAll();
}

