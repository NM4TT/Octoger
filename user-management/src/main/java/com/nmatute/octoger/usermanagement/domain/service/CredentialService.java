package com.nmatute.octoger.usermanagement.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;
import com.nmatute.octoger.usermanagement.web.security.AES;
import com.nmatute.octoger.usermanagement.web.security.AES.Action;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository repo;

    private final AES AES = new AES();
 
    public CredentialDTO get(String username) {
        CredentialDTO credential = repo.get(username);
        credential.setPassword(AES.perform(credential.getPassword(), Action.DECRYPT));
        return credential;
    }
    
    public CredentialDTO save(CredentialDTO credential) {
        credential.setPassword(AES.perform(credential.getPassword(), Action.ENCRYPT));
        return repo.save(credential);
    }
    
    public int findIdByUsername(String username){
        return repo.findIdByUsername(username);
    }

    public Optional<CredentialDTO> findByUsername(String username){
        return Optional.of(repo.findByUsername(username));
    }

}
