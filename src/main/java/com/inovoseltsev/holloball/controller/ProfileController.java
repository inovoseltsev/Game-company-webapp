package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userFullName")
public class ProfileController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/home")
    public String displayProfilePage(Authentication authentication,
                                     ModelMap model) {
        if (authentication == null) {
            return "redirect:/login";
        }
        AppUser user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        String userFullName = user.getFirstName() + " " + user.getLastName();
        model.addAttribute("userFullName", userFullName);
        if (user.getRole().equals(Role.ADMIN)) {
            List<AppUser> users = new ArrayList<>();
            for (AppUser appUser : userService.findAll()) {
                if (appUser.getRole().equals(Role.USER)) {
                    users.add(appUser);
                }
            }
            model.addAttribute("users", users);
            return "admin";
        } else {
            return "user";
        }
    }
}
