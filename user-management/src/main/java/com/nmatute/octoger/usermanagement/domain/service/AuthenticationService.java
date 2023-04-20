package com.nmatute.octoger.usermanagement.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.json.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.json.RegisterRequest;

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
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final TypeService typeService;
    private final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest request) {
        UserDTO user = new UserDTO();
        CredentialDTO credential = new CredentialDTO();

        log.debug("Request structure: " + request.toString());

        if(!request.isEmpty(request.getName()) &&
            !request.isEmpty(request.getLastname()) &&
            !request.isEmpty(request.getPersonalIdentifier()) &&
            !request.isEmpty(request.getType()) && request.getType().startsWith("USR") &&
            !request.isEmpty(request.getUsername()) &&
            !request.isEmpty(request.getPassword())
        ){
            user.setName(request.getName());
            user.setLastname(request.getLastname());
            user.setPersonalIdentifier(request.getPersonalIdentifier());
            user.setType(typeService.getByIdentifier(request.getType()));
            log.debug("User data recolected for registration.");
            user = userService.save(user);
            log.debug("User saved.");

            credential.setUsername(request.getUsername());
            credential.setUser(user);
            credential.setRole((user.getType().getIdentifier().endsWith("00") ? Role.ADMIN : Role.REGULAR));
            credential.setPassword(request.getPassword());
            
            log.debug("Credential data recolected for registration.");
            
            credentialService.save(credential);
            log.debug("Credential saved");

            String jwtToken = jwtService.generateToken(credential);
            log.debug("JWT Token generated in registration.");

            return new AuthenticationResponse(jwtToken);
        }

        return null;
    }

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