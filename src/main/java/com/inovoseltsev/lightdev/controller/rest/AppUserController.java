package com.inovoseltsev.lightdev.controller.rest;

import com.inovoseltsev.lightdev.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appUsers")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;


}
