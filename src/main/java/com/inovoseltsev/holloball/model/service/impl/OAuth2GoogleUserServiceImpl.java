package com.inovoseltsev.holloball.model.service.impl;

import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import com.inovoseltsev.holloball.model.repository.OAuth2GoogleUserRepository;
import com.inovoseltsev.holloball.model.service.OAuth2GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OAuth2GoogleUserServiceImpl implements OAuth2GoogleUserService {

    @Autowired
    private OAuth2GoogleUserRepository OAuth2GoogleUserRepository;

    @Override
    @Transactional
    public void create(OAuth2GoogleUser user) {
        OAuth2GoogleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void update(OAuth2GoogleUser user) {
        OAuth2GoogleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(OAuth2GoogleUser user) {
        OAuth2GoogleUserRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public OAuth2GoogleUser findByEmail(String email) {
        return OAuth2GoogleUserRepository.findAuthUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OAuth2GoogleUser> findAll() {
        return (List<OAuth2GoogleUser>) OAuth2GoogleUserRepository.findAll();
    }
}
