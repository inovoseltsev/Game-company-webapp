package com.inovoseltsev.lightdev.security.oauth2;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2UserImpl implements OAuth2User {
    private final Map<String, Object> userAttributes;

    public OAuth2UserImpl(Map<String, Object> userAttributes) {
        this.userAttributes = userAttributes;
    }

    @Override
    public <A> A getAttribute(String name) {
        return (A) userAttributes.get(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return userAttributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    @Override
    public String getName() {
        return (String) userAttributes.get("email");
    }
}
