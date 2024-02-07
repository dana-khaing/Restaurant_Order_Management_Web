package com.oaxaca.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.oaxaca.customer_service.config.oauth.Oauth2AuthenticationEntrypoint;
import com.oaxaca.customer_service.config.oauth.Oauth2LoginSuccessHandler;
import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.model.CustomerDetails;

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
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated())
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

    @Bean
    PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

    @Bean
    UserDetailsService userDetailsService() {
    Customer customer = new Customer("user", passwordEncoder().encode("password"), "email");
    UserDetails userDetails = new CustomerDetails(customer);
    return new InMemoryUserDetailsManager(userDetails);
  }

}
