package com.inovoseltsev.lightdev.security.oauth2;

import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

public class OAuth2UserServiceImpl implements OAuth2UserService<OAuth2UserRequest, OAuth2UserImpl> {
    @Override
    public OAuth2UserImpl loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        String userInfoEndpointUri =
                userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUri();
        if (!StringUtils.isEmpty(userInfoEndpointUri)) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION,
                    "Bearer " + userRequest.getAccessToken()
                    .getTokenValue());
            HttpEntity entity = new HttpEntity("", headers);
            ResponseEntity<Map> response = restTemplate
                    .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
            Map<String, Object> userAttributes = response.getBody();
            return new OAuth2UserImpl(userAttributes);
        } else {
            throw new RuntimeException("User url is empty!");
        }
    }
}
