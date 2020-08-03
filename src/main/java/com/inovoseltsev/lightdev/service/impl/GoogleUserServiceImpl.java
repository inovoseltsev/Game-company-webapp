package com.inovoseltsev.lightdev.service.impl;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.repository.GoogleUserRepository;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleUserServiceImpl implements GoogleUserService {

    @Autowired
    private GoogleUserRepository googleUserRepository;

    @Override
    @Transactional
    public void create(GoogleUser user) {
        googleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void update(GoogleUser user) {
        googleUserRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(GoogleUser user) {
        googleUserRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public GoogleUser findById(Long id) {
        Optional<GoogleUser> user = googleUserRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public GoogleUser findByEmail(String email) {
        return googleUserRepository.findAuthUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GoogleUser> findAll() {
        return (List<GoogleUser>) googleUserRepository.findAll();
    }
}
