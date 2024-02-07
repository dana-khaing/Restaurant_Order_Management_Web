package com.oaxaca.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.oaxaca.customer_service.config.oauth.Oauth2AuthenticationEntrypoint;
import com.oaxaca.customer_service.config.oauth.Oauth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()
        )
        .exceptionHandling(exceptionHandlingConfigurer -> {
          exceptionHandlingConfigurer.authenticationEntryPoint(
              new Oauth2AuthenticationEntrypoint());
        })
        .oauth2Login(customizer -> {
          customizer
              .successHandler(new Oauth2LoginSuccessHandler());
        });
    return http.build();
  }
}
