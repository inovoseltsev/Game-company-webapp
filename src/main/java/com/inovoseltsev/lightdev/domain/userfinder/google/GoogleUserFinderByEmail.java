package com.inovoseltsev.lightdev.domain.userfinder.google;

import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.userfinder.UserServiceFinder;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleUserFinderByEmail implements UserServiceFinder {

    @Autowired
    private GoogleUserService userService;

    @Override
    public GoogleUser findUser(Object email) {
        return userService.findByEmail((String) email);
    }
}
