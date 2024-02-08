package com.oaxaca.kitchen_staff_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;
import com.oaxaca.kitchen_staff_service.service.KitchenStaffDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());

    return new ProviderManager(authenticationProvider);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/kitchen_staff/login", "/kitchen_staff/register").permitAll()
            .anyRequest().authenticated());
    return http.build();
  }

  @Bean
  KitchenStaffDetailsService userDetailsService(KitchenStaffRepository kitchenStaffRepository) {
    KitchenStaff kitchenStaff = new KitchenStaff("jacky123", passwordEncoder().encode("password"), "Jack", "Brown", "chef");
    kitchenStaffRepository.save(kitchenStaff);
    return new KitchenStaffDetailsService(kitchenStaffRepository);
  }

}
