package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialServiceTest {

    private final CredentialRepository repo;

    public Optional<CredentialDTO> findByUsername(String username){
        return Optional.of(repo.findByUsername(username));
    }

}
