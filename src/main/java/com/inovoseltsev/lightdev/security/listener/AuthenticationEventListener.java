package com.inovoseltsev.lightdev.security.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if(event instanceof InteractiveAuthenticationSuccessEvent) {
            return;
        }
        Authentication authentication = event.getAuthentication();
        String authMessage = "Login attempt with user name: "
                + authentication.getName() + "  "
                + authentication.getAuthorities() + "  "
                + authentication.getCredentials()+ "  "
                + authentication.getPrincipal() + "  "
                + authentication.getDetails();
        System.out.println(authMessage);
    }
}
