package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import com.inovoseltsev.holloball.oauth2.OAuth2UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2UserImpl> oAuth2UserService;

    @GetMapping("/home")
    public String displayProfilePage(Authentication authentication,
                                     ModelMap model,
                                     HttpSession session) {
        if (authentication == null) {
            return "sign-in";
        } else if (authentication.getPrincipal().toString().contains(" sub=")) {
            return "redirect:/oAuth2Success";
        }
        System.out.println(authentication.getPrincipal());
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

    @GetMapping("/oAuth2Success")
    public String getLoginData(OAuth2AuthenticationToken authenticationToken,
                               HttpSession session, Model model) {
        session.setAttribute("oAuth2", true);
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName());
        OAuth2UserImpl oAuth2User =
                oAuth2UserService.loadUser(new OAuth2UserRequest(client
                        .getClientRegistration(), client.getAccessToken()));
        model.addAttribute("userFullNAme", oAuth2User.getName());
        return "user";
    }
}
