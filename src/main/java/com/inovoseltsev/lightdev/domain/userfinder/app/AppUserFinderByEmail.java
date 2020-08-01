package com.inovoseltsev.lightdev.domain.userfinder.app;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.domain.userfinder.UserServiceFinder;
import com.inovoseltsev.lightdev.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUserFinderByEmail implements UserServiceFinder {

    @Autowired
    private AppUserService appUserService;

    @Override
    public AppUser findUser(Object email) {
        return appUserService.findByEmail((String) email);
    }
}
