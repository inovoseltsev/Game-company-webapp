package com.inovoseltsev.lightdev.service.impl;

import com.inovoseltsev.lightdev.domain.OAuth2GoogleUser;
import com.inovoseltsev.lightdev.repository.OAuth2GoogleUserRepository;
import com.inovoseltsev.lightdev.service.OAuth2GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OAuth2GoogleUserServiceImpl implements OAuth2GoogleUserService {

    @Autowired
    private OAuth2GoogleUserRepository oAuth2GoogleUserRepository;

    @Override
    @Transactional
    public void create(OAuth2GoogleUser user) {
        oAuth2GoogleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void update(OAuth2GoogleUser user) {
        oAuth2GoogleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(OAuth2GoogleUser user) {
        oAuth2GoogleUserRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public OAuth2GoogleUser findById(String id) {
        Optional<OAuth2GoogleUser> user = oAuth2GoogleUserRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public OAuth2GoogleUser findByEmail(String email) {
        return oAuth2GoogleUserRepository.findAuthUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OAuth2GoogleUser> findAll() {
        return (List<OAuth2GoogleUser>) oAuth2GoogleUserRepository.findAll();
    }
}
