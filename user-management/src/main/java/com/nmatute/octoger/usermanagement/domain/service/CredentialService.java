package com.nmatute.octoger.usermanagement.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;
import com.nmatute.octoger.usermanagement.web.security.DES;
import com.nmatute.octoger.usermanagement.web.security.DES.Action;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository repo;

    private final DES DES = new DES();
 
    public CredentialDTO get(int userId) {
        CredentialDTO credential = repo.get(userId);
        credential.setUsername(DES.perform(credential.getUsername(), Action.DECRYPT));
        credential.setPassword(DES.perform(credential.getPassword(), Action.DECRYPT));
        return credential;
    }

    
    public String getUsername(int userId) {
        return DES.perform(repo.getUsername(userId), Action.DECRYPT);
    }

    
    public String getPassword(int userId) {
        return DES.perform(repo.getPassword(userId), Action.DECRYPT);
    }

    
    public CredentialDTO save(CredentialDTO credential) {
        credential.setUsername(DES.perform(credential.getUsername(), Action.ENCRYPT));
        credential.setPassword(DES.perform(credential.getPassword(), Action.ENCRYPT));
        return repo.save(credential);
    }
    
    public int findIdByUserId(int userId){
        return repo.findIdByUserId(userId);
    }

    public Optional<CredentialDTO> findUserByUsername(String username){
        return Optional.of(repo.findUserByUsername(username));
    }

}
