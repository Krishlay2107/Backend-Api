package com.springbootProject.Journalapp.configure;


import com.springbootProject.Journalapp.Filter.JwtFilter;
import com.springbootProject.Journalapp.services.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private JwtFilter jwtFilter;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(request -> request
                    .requestMatchers("/Public/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()

                    .requestMatchers("/API/**").permitAll()
                    .requestMatchers("/journal/**","/user/**").authenticated()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().permitAll())
                      .csrf(AbstractHttpConfigurer::disable)
                       .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                       .build();
    }


@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
{
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
}
     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

}
