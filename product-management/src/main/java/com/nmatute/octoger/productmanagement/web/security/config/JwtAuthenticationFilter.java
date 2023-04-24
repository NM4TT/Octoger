package com.nmatute.octoger.productmanagement.web.security.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nmatute.octoger.productmanagement.domain.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Filtro de tokens JWT.
 * 
 * @author NM4TT
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    /**
     * Method executed every time the client send a request.
     */
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) 
    throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        //Checks JWT token existence
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug( "JWT Token not found.");
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);

        username = jwtService.extractUsername(jwtToken);

        //Checks if sender (user) exists and is already checked
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("User will be verified for first time.");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtService.isTokenValid(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(userDetails, 
                null, 
                userDetails.getAuthorities());

                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.debug("User verified.");
            }
        }
        log.debug("Response filtered.");
        filterChain.doFilter(request, response);
    }
    
}
