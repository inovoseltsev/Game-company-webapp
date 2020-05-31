package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import com.inovoseltsev.holloball.model.service.OAuth2GoogleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes({"userFullName", "userId", "isOAuth2"})
public class UserController {
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private OAuth2GoogleUserService auth2GoogleUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String setUserPage() {
        return "redirect:/events";
    }

    @GetMapping("/makeMeAdmin")
    public String makeUserAdmin(HttpServletRequest req, Authentication auth) {
        String password = req.getParameter("password");
        if (password != null && password.equals("mmfffm")) {
            AppUser user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
            user.setRole(Role.ADMIN);
            appUserService.update(user);
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
        return "user";
    }

    @GetMapping("/events")
    public String setEventsPage(HttpSession session,
                                Model model) {
        if (session.getAttribute("userFullName") == null) {
            return "redirect:/home";
        }
        model.addAttribute("events", true);
        return "user";
    }

    @GetMapping("/profile")
    public String setProfilePage(Model model, @SessionAttribute String userId,
                                 @SessionAttribute Boolean isOAuth2,
                                 HttpServletRequest req) {
        model.addAttribute("profile", true);
        if (req.getParameterMap().containsKey("errorMessage")) {
            model.addAttribute("errorMessage",
                    req.getParameter("errorMessage"));
        }
        if (isOAuth2) {
            OAuth2GoogleUser user = auth2GoogleUserService.findById(userId);
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("email", user.getEmail());
        } else {
            AppUser user = appUserService.findById(Long.parseLong(userId));
            model.addAttribute("firstName", user.getFirstName());
            model.addAttribute("lastName", user.getLastName());
            model.addAttribute("email", user.getEmail());
        }
        return "user";
    }

    @PostMapping("/saveProfile")
    public String updateProfilePage(@RequestParam Map<String, String> userData,
                                    @SessionAttribute String userId,
                                    @SessionAttribute Boolean isOAuth2,
                                    RedirectAttributes redirectAttributes) {
        String firstName = userData.get("firstName");
        String lastName = userData.get("lastName");
        String email = userData.get("email");
        String password = userData.get("password");
        String newPassword = userData.get("newPassword");
        if (isOAuth2) {
            OAuth2GoogleUser user = auth2GoogleUserService.findById(userId);
            updateOAuth2User(user, firstName, lastName);
        } else {
            AppUser user = appUserService.findById(Long.parseLong(userId));
            if (!passwordEncoder.matches(password, user.getPassword())) {
                redirectAttributes.addAttribute("errorMessage", "You entered"
                        + " incorrect password");
                return "redirect:/profile";
            }
            updateAppUser(user, firstName, lastName, email, newPassword);
        }
        return "redirect:/profile";
    }

    private void updateOAuth2User(OAuth2GoogleUser user, String firstName,
                                  String lastName) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        auth2GoogleUserService.update(user);
    }

    private void updateAppUser(AppUser user, String firstName,
                               String lastName, String email,
                               String newPassword) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        appUserService.update(user);
    }
}
