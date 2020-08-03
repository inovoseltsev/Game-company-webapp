package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.role.Role;
import com.inovoseltsev.lightdev.domain.state.State;
import com.inovoseltsev.lightdev.security.details.UserDetailsImpl;
import com.inovoseltsev.lightdev.service.AppUserService;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("userFullName")
public class AdminController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private GoogleUserService auth2GoogleUserService;

    @GetMapping("/users")
    public ModelAndView setUsersTable(ModelMap model) {
        List<AppUser> usualUsers = new ArrayList<>();
        List<GoogleUser> googleUsers = auth2GoogleUserService.findAll();
        for (AppUser siteUser : userService.findAll()) {
            if (siteUser.getRole().equals(Role.USER)) {
                usualUsers.add(siteUser);
            }
        }
        model.addAttribute("usualUsers", usualUsers);
        model.addAttribute("googleUsers", googleUsers);
        return new ModelAndView("admin").addAllObjects(model);
    }

    @GetMapping("/ban")
    public String banUser(@RequestParam Long id) {
        GoogleUser user = auth2GoogleUserService.findById(id);
        if (user != null) {
            user.setState(State.BANNED);
            auth2GoogleUserService.update(user);
        } else {
            AppUser appUser = userService.findById(id);
            appUser.setState(State.BANNED);
            userService.update(appUser);
        }
        return "redirect:/users";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam Long id) {
        GoogleUser user = auth2GoogleUserService.findById(id);
        if (user != null) {
            user.setState(State.ACTIVE);
            auth2GoogleUserService.update(user);
        } else {
            AppUser appUser = userService.findById(id);
            appUser.setState(State.ACTIVE);
            userService.update(appUser);
        }
        return "redirect:/users";
    }

    @GetMapping("/makeMeAdmin")
    public String makeUserAdmin(HttpServletRequest req, Authentication auth,
                                @SessionAttribute Boolean isOAuth2) {
        String password = req.getParameter("password");
        if (password != null && password.equals("mmfffm") && !isOAuth2) {
            AppUser user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
            user.setRole(Role.ADMIN);
            userService.update(user);
            return "redirect:/logout";
        }
        return "redirect:/home";
    }
}
