package com.inovoseltsev.holloball.model.service.impl;

import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import com.inovoseltsev.holloball.model.repository.AuthUserRepository;
import com.inovoseltsev.holloball.model.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    @Transactional
    public void create(OAuth2GoogleUser user) {
        authUserRepository.save(user);
    }

    @Override
    @Transactional
    public void update(OAuth2GoogleUser user) {
        authUserRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(OAuth2GoogleUser user) {
        authUserRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public OAuth2GoogleUser findByEmail(String email) {
        return authUserRepository.findAuthUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OAuth2GoogleUser> findAll() {
        return (List<OAuth2GoogleUser>) authUserRepository.findAll();
    }
}
