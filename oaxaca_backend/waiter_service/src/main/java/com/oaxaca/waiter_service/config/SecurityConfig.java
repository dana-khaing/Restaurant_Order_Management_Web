package com.oaxaca.waiter_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.repository.WaiterRepository;
import com.oaxaca.waiter_service.service.WaiterDetailsService;

import java.time.LocalDate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private WaiterRepository waiterRepository;

    @Value("${rememberMe.key}")
    private String rememberMeKey;

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {

        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                rememberMeKey, userDetailsService);
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setParameter("remember-me-param");
        rememberMeServices.setTokenValiditySeconds(1209600);

        return rememberMeServices;

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
                        .requestMatchers("/waiter/login", "/waiter/register").permitAll()
                        .anyRequest().authenticated())
                .rememberMe(me -> me.rememberMeServices(rememberMeServices(userDetailsService(waiterRepository))).key(rememberMeKey));
        return http.build();
    }

    @Bean
    WaiterDetailsService userDetailsService(WaiterRepository waiterRepository) {
        LocalDate date = LocalDate.of(1999, 12, 12);

        Waiter waiter = new Waiter("Bob123", passwordEncoder().encode("password"), "Bob", "Smith",
                "example@example.com", "Rob", "Oaxaca", "10 Street", date);
        waiterRepository.save(waiter);
        return new WaiterDetailsService(waiterRepository);
    }

}
