package com.inovoseltsev.holloball.controller;

import com.inovoseltsev.holloball.model.details.UserDetailsImpl;
import com.inovoseltsev.holloball.model.entity.AppUser;
import com.inovoseltsev.holloball.model.entity.OAuth2GoogleUser;
import com.inovoseltsev.holloball.model.role.Role;
import com.inovoseltsev.holloball.model.service.AppUserService;
import com.inovoseltsev.holloball.model.service.OAuth2GoogleUserService;
import com.inovoseltsev.holloball.oauth2.OAuth2UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private OAuth2GoogleUserService auth2GoogleUserService;

    @GetMapping("/home")
    public String displayProfilePage(Authentication authentication,
                                     ModelMap model,
                                     HttpSession session) {
        if (authentication == null) {
            return "sign-in";
        } else if (authentication.getPrincipal().toString().contains(" sub=")) {
            return "redirect:/oAuth2Success";
        }
        AppUser user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        String userFullName = user.getFirstName() + " " + user.getLastName();
        session.setAttribute("isOAuth2", false);
        session.setAttribute("userId", user.getUserId());
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
            session.setAttribute("userFullName", userFullName);
            return "redirect:/events";
        }
    }

    @GetMapping("/oAuth2Success")
    public String getLoginData(OAuth2AuthenticationToken authenticationToken,
                               HttpSession session) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName());
        OAuth2UserImpl oAuth2User =
                oAuth2UserService.loadUser(new OAuth2UserRequest(client
                        .getClientRegistration(), client.getAccessToken()));
        if (auth2GoogleUserService.findByEmail(oAuth2User.getName()) == null) {
            OAuth2GoogleUser user =
                    new OAuth2GoogleUser(oAuth2User.getAttributes());
            auth2GoogleUserService.create(user);
            session.setAttribute("userFullName", user.getName());
            session.setAttribute("userId", user.getId());
        } else {
            session.setAttribute("userFullName", oAuth2User.getAttribute(
                    "name"));
            session.setAttribute("userId", oAuth2User.getAttribute("sub"));
        }
        session.setAttribute("isOAuth2", true);
        return "redirect:/events";
    }
}
