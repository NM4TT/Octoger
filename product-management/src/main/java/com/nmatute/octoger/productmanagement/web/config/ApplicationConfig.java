package com.nmatute.octoger.productmanagement.web.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nmatute.octoger.productmanagement.domain.service.CredentialService;
import com.nmatute.octoger.productmanagement.web.security.AES;

import lombok.RequiredArgsConstructor;

/**
 * Clase para beans extra.
 * 
 * @author NM4TT
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    
    private final CredentialService service;
    private final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Bean
    public UserDetailsService userDetailsService(){
        log.debug("UserDetailsService configured.");
        return username -> Optional.of(service.findByUsername(username))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        log.debug("AuthenticationProvider configured.");
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("PasswordEncoder configured.");
        return new AES();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        log.debug("AuthenticationManager configured.");
        return authConfig.getAuthenticationManager();
    }

}
