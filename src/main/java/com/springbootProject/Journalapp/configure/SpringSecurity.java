package com.springbootProject.Journalapp.configure;


import com.springbootProject.Journalapp.services.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    private UserDetailsServiceImp userDetailsService;
    private  PasswordEncoder passwordEncoder;

@Autowired
    public SpringSecurity(@Lazy  UserDetailsServiceImp userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(request -> request
            .requestMatchers("/public/**").permitAll()

            .requestMatchers("/journal/**","/user/**").authenticated()
            .requestMatchers("/user/post").permitAll()  // Allowing access to create new user
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()) .httpBasic(Customizer.withDefaults())

            .csrf(AbstractHttpConfigurer::disable) .build(); }


@Autowired public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
{
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder); }
}
