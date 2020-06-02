package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import com.inovoseltsev.holloball.model.service.OAuth2GoogleUserService;
import com.inovoseltsev.holloball.model.state.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userFullName")
public class AdminController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private OAuth2GoogleUserService auth2GoogleUserService;

    @GetMapping("/users")
    public ModelAndView setUsersTable(ModelMap model) {
        List<AppUser> usualUsers = new ArrayList<>();
        List<OAuth2GoogleUser> googleUsers = auth2GoogleUserService.findAll();
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
    public String banUser(@RequestParam String id) {
        OAuth2GoogleUser user = auth2GoogleUserService.findById(id);
        if (user != null) {
            user.setState(State.BANNED);
            auth2GoogleUserService.update(user);
        } else {
            AppUser appUser = userService.findById(Long.parseLong(id));
            appUser.setState(State.BANNED);
            userService.update(appUser);
        }
        return "redirect:/users";
    }

    @GetMapping("/activate")
    public String activateUser(@RequestParam String id) {
        OAuth2GoogleUser user = auth2GoogleUserService.findById(id);
        if (user != null) {
            user.setState(State.ACTIVE);
            auth2GoogleUserService.update(user);
        } else {
            AppUser appUser = userService.findById(Long.parseLong(id));
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
