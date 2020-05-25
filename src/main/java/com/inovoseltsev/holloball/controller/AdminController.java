package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(value = "userFullName")
public class AdminController {

    @Autowired
    private AppUserService userService;

    @GetMapping("/users")
    public ModelAndView setUsersTable(ModelMap model) {
        List<AppUser> users = new ArrayList<>();
        for (AppUser siteUser : userService.findAll()) {
            if (siteUser.getRole().equals(Role.USER)) {
                users.add(siteUser);
            }
        }
        model.addAttribute("users", users);
        return new ModelAndView("admin").addAllObjects(model);
    }
}
