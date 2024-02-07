package com.oaxaca.customer_service.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.oaxaca.customer_service.model.AppAuthenticationToken;
import com.oaxaca.customer_service.model.AppUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("User has logged in with Google");
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        AppUser appUser = AppUser.fromGoogleUser(oidcUser);
        AppAuthenticationToken token = new AppAuthenticationToken(appUser);
        SecurityContextHolder.getContext().setAuthentication(token);
        response.sendRedirect("http://localhost:3000/customer/home");
    }
}