package com.oursummer.vacit.service;

import com.oursummer.vacit.domain.User;
import com.oursummer.vacit.dto.CustomOAuth2User;
import com.oursummer.vacit.dto.NaverResponse;
import com.oursummer.vacit.dto.OAuth2Response;
import com.oursummer.vacit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService { //OAuth2UserService의 구현체

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else { //google, kakao, github 등 추가 가능
            return null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User user = userRepository.findByUsername(username);

        String role = "ROLE_USER";
        if (user == null) {
            user = new User();
            user.setRole(role);

        } else {
            role = user.getRole();
        }
        user.setUsername(username);
        user.setEmail(oAuth2Response.getEmail());
        userRepository.save(user);

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
