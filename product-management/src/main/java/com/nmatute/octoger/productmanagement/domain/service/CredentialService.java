package com.nmatute.octoger.productmanagement.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialRepository repo;

    public Optional<CredentialDTO> findByUsername(String username){
        return Optional.of(repo.findByUsername(username));
    }

}
