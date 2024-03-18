package com.oaxaca.customer_service.model;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import lombok.Getter;

@Getter
public class AppUser {
  private String id;
  private String name;
  private String email;
  private String imageUrl;

  public static AppUser fromGoogleUser(DefaultOidcUser googleUser) {
    AppUser appUser = new AppUser();
    appUser.id = googleUser.getSubject();
    appUser.name = googleUser.getFullName();
    appUser.email = googleUser.getEmail();
    appUser.imageUrl = googleUser.getPicture();
    return appUser;
  }
}
