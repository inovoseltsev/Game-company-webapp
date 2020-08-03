package com.inovoseltsev.lightdev.controller;

import com.inovoseltsev.lightdev.domain.entity.AppUser;
import com.inovoseltsev.lightdev.domain.entity.GoogleUser;
import com.inovoseltsev.lightdev.domain.role.Role;
import com.inovoseltsev.lightdev.domain.state.State;
import com.inovoseltsev.lightdev.security.details.UserDetailsImpl;
import com.inovoseltsev.lightdev.security.oauth2.OAuth2UserImpl;
import com.inovoseltsev.lightdev.service.GoogleUserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private OAuth2UserService<OAuth2UserRequest, OAuth2UserImpl> oAuth2UserService;

    @Autowired
    private GoogleUserService auth2GoogleUserService;

    @GetMapping("/home")
    public String displayProfilePage(Authentication authentication,
                                     HttpSession session) {
        if (authentication == null) {
            return "sign-in";
        } else if (authentication.getPrincipal().toString().contains(" sub=")) {
            return "redirect:/oAuth2Success";
        }
        AppUser user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
        String userFullName = user.getFirstName() + " " + user.getLastName();
        session.setAttribute("isOAuth2", false);
        session.setAttribute("userId", user.getId());
        session.setAttribute("userFullName", userFullName);
        if (user.getRole().equals(Role.ADMIN)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/users";
        } else {
            session.setAttribute("isAdmin", false);
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
        GoogleUser user =
                auth2GoogleUserService.findByEmail(oAuth2User.getName());
        if (user == null) {
            user = new GoogleUser(oAuth2User.getAttributes());
            auth2GoogleUserService.create(user);
            session.setAttribute("userFullName", user.getNickname());
            session.setAttribute("userId", user.getId());
        } else {
            if (user.getState().equals(State.BANNED)) {
                return "redirect:/failed?bannedGoogleAccount";
            }
            session.setAttribute("userFullName", oAuth2User.getAttribute(
                    "name"));
            session.setAttribute("userId", user.getId());
        }
        session.setAttribute("isOAuth2", true);
        session.setAttribute("isAdmin", false);
        return "redirect:/events";
    }
}
