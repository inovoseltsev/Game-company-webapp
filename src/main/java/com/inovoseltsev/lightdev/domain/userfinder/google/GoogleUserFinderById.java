package com.inovoseltsev.lightdev.domain.userfinder.google;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.userfinder.UserServiceFinder;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleUserFinderById implements UserServiceFinder {

    @Autowired
    private GoogleUserService googleUserService;

    @Override
    public GoogleUser findUser(Object id) {
        return googleUserService.findById((Long) id);
    }
}
