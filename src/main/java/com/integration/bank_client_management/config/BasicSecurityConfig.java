package com.integration.bank_client_management.config;

import com.integration.bank_client_management.security.BasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {
    @Value("${security.admin.username}")
    private String adminUsername;
    @Value("${security.admin.password}")
    private String adminPassword;
    private final BasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public BasicSecurityConfig(BasicAuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser(adminUsername)
                .password(passwordEncoder().encode(adminPassword))
                .roles("ADMIN");
    }
}