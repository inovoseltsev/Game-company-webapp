package com.inovoseltsev.holloball.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/sign-in")
    public String displayLoginPage(HttpServletRequest req, ModelMap model,
                                   Authentication authentication,
                                   HttpSession session) {
        session.setAttribute("oAuth2", false);
        if (authentication != null) {
            return "redirect:/home";
        }
        if (req.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "sign-in";
    }
}
