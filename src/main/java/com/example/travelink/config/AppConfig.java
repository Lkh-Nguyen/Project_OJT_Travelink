package com.example.travelink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/", "/CustomerLoginRegister", "/css/**").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/CustomerLoginRegister")
                        .defaultSuccessUrl("/CustomerHome", true))
                .oauth2Login(login -> login
                        .loginPage("/CustomerLoginRegister")
                        .defaultSuccessUrl("/OAuthCustomerHome", true));

        return http.build();
    }
}