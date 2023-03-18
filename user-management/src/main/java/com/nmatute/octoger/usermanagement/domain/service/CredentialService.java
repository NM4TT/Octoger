package com.nmatute.octoger.usermanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.CredentialRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CredentialService {

    private CredentialRepository repo;
 
    public CredentialDTO get(int userId) {
        return repo.get(userId);
    }

    
    public String getUsername(int userId) {
        return repo.getUsername(userId);
    }

    
    public String getPassword(int userId) {
        return repo.getPassword(userId);
    }

    
    public CredentialDTO save(CredentialDTO credential) {
        return repo.save(credential);
    }
    
    public int findIdByUserId(int userId){
        return repo.findIdByUserId(userId);
    }

}
