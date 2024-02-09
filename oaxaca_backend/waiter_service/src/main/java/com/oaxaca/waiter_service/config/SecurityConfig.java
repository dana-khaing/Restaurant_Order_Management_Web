package com.oaxaca.waiter_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

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
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter
                .setAuthenticationManager(authenticationManager(userDetailsService(waiterRepository)));
        usernamePasswordAuthenticationFilter.setRememberMeServices(rememberMeServices());
        return usernamePasswordAuthenticationFilter;
    }

    @Bean
    RememberMeServices rememberMeServices() {
        RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;

        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                rememberMeKey, userDetailsService(waiterRepository), encodingAlgorithm);
        rememberMeServices.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
        rememberMeServices.setCookieName("rememberMe");
        rememberMeServices.setTokenValiditySeconds(1209600);
        rememberMeServices.setAlwaysRemember(true);

        return rememberMeServices;

    }

    @Bean
    RememberMeAuthenticationFilter rememberMeFilter() {
        RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(
                authenticationManager(userDetailsService(waiterRepository)), rememberMeServices());
        return rememberMeFilter;
    }

    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider(
                rememberMeKey);
        return rememberMeAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/waiter/login", "/waiter/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(usernamePasswordAuthenticationFilter(), RememberMeAuthenticationFilter.class)
                .addFilterBefore(rememberMeFilter(), RememberMeAuthenticationFilter.class);
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
