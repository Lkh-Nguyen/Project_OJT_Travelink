package com.example.travelink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //Create config of webpage
                .authorizeHttpRequests(requests -> requests
                        // .requestMatchers("/", "/CustomerLoginRegister", "/css/**", "/UploadImageAvatar", "/CustomerViewAvatar","/CustomerUpdateAvatar","/hello").permitAll()
                        .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .formLogin(login -> login
                        .loginPage("/CustomerLoginRegister")
                        .defaultSuccessUrl("/CustomerHome", true));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}