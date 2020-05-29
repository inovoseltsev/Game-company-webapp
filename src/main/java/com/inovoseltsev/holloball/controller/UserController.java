package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private AppUserService userService;

    @GetMapping("/user")
    public String setUserPage(@SessionAttribute String userFullName,
                              ModelMap model) {
        model.addAttribute("userFullName", userFullName);
        return "user";
    }

    @GetMapping("/makeMeAdmin")
    public String makeUserAdmin(HttpServletRequest req, Authentication auth) {
        String password = req.getParameter("password");
        if (password != null && password.equals("mmfffm")) {
            AppUser user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
            user.setRole(Role.ADMIN);
            userService.update(user);
        }
        return "redirect:/home";
    }

    @GetMapping("/games")
    public String setGamesPage(HttpSession session,
                               Model model) {
        if (session.getAttribute("userFullName") == null) {
            return "redirect:/home";
        }
        model.addAttribute("games", true);
        model.addAttribute("userFullName", session.getAttribute("userFullName"));
        return "user";
    }

    @GetMapping("/events")
    public String setEventsPage(HttpSession session,
                                Model model) {
        if (session.getAttribute("userFullName") == null) {
            return "redirect:/home";
        }
        model.addAttribute("events", true);
        model.addAttribute("userFullName", session.getAttribute("userFullName"));
        return "user";
    }
}
