package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.web.security.auth.AuthenticationRequest;
import com.nmatute.octoger.usermanagement.web.security.auth.AuthenticationResponse;
import com.nmatute.octoger.usermanagement.web.security.auth.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EntityService entityService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest request) {
        UserDTO user = new UserDTO();
        CredentialDTO credential = new CredentialDTO();

        user.setName(request.getFirstName());
        user.setLastname(request.getLastname());
        user.setPersonalIdentifier(request.getPersonalIdentifier());
        user.setType(request.getType());

        credential.setUsername(request.getUsername());
        credential.setUser(user);
        credential.setRole((user.getType().endsWith("00") ? Role.ADMIN : Role.REGULAR));
        credential.setPassword(request.getPassword());

        entityService.getUserService().save(user);
        entityService.getCredentialService().save(credential);

        String jwtToken = jwtService.generateToken(credential);

        return new AuthenticationResponse(jwtToken);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword()
            )
        );

        CredentialDTO credential = entityService.getCredentialService()
        .findUserByUsername(request.getUsername())
        .orElseThrow();

        String jwtToken = jwtService.generateToken(credential);

        return new AuthenticationResponse(jwtToken);
    }
    
}