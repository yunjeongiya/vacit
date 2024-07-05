package com.oursummer.vacit.config;

import com.oursummer.vacit.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll() // h2-console 경로에 대한 접근 허용
                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        .requestMatchers("/**").permitAll() // 개발중 모든 사용자에게 허용
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable // CSRF 보호 비활성화 (h2-console 사용 시 필요)
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // X-Frame-Options 비활성화 (h2-console 사용 시 필요)
                );


//        http
//                .csrf((csrf) -> csrf.disable())
//                .formLogin((login) -> login.disable())
//                .httpBasic((basic) -> basic.disable())
//                .oauth2Login((oauth2) -> oauth2
//                        .userInfoEndpoint((userInfoEndpointConfig ->
//                                userInfoEndpointConfig.userService(customOAuth2UserService))))
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
//                        .anyRequest().authenticated());

        return http.build();
    }
}
