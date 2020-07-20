package com.inovoseltsev.lightdev.controller.rest;

import com.inovoseltsev.lightdev.domain.OAuth2GoogleUser;
import com.inovoseltsev.lightdev.service.OAuth2GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    @Autowired
    private OAuth2GoogleUserService userService;

    @RequestMapping("/getUserData")
    public @ResponseBody
    OAuth2GoogleUser getUserData() {
        return userService.findByEmail("drag556321@gmail.com");
    }
}
