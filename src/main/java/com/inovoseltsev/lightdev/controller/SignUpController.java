package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.model.validator.UserValidator;
import com.inovoseltsev.lightdev.model.entity.AppUser;
import com.inovoseltsev.lightdev.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SignUpController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/sign-up")
    public String displaySignUpPage() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@RequestParam Map<String, String> userParameters,
                             ModelMap model) {
        AppUser user = userValidator.build(userParameters);
        if (user == null) {
            model.addAttribute("errorMessage", userValidator.getErrorMessage());
            model.addAttribute("firstName", userParameters.get("firstName"));
            model.addAttribute("lastName", userParameters.get("lastName"));
            model.addAttribute("login", userParameters.get("login"));
            model.addAttribute("email", userParameters.get("email"));
            return "sign-up";
        }
        userService.create(user);
        return "sign-in";
    }
}
