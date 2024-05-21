package com.clientside.mbkm.Configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        return http
                .authorizeRequests(auth -> auth
                        .requestMatchers("/css/**", "/img/**", "/js/**")
                        .permitAll()
                        .requestMatchers("/login/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successForwardUrl("/home")
                        .failureForwardUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout").permitAll())
                .build();
    }
}