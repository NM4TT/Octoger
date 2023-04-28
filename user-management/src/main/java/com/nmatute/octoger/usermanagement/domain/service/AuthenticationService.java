package com.nmatute.octoger.usermanagement.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationResponse;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Autenticacion.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CredentialService credentialService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        if (!request.isEmpty(request.getUsername()) || !request.isEmpty(request.getPassword())) {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), 
                    request.getPassword()
                )
            );

            CredentialDTO credentialDTO = credentialService.findByUsername(request.getUsername());

            if (credentialDTO != null) {

                String jwtToken = jwtService.generateToken(credentialDTO);
                log.debug("JWT Token generated in authenticate.");
        
                return new AuthenticationResponse(jwtToken);
            }
    
            return null;
        }
        log.debug(request.getUsername() + " " + request.getPassword());
        return null;
    }
    
}