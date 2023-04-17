package com.nmatute.octoger.accountingmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.CredentialMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CredentialRepository {
    
    private final ICredentialCrudRepository crud;
    private final CredentialMapper mapper;

    public CredentialDTO findByUsername(String username){
        return mapper.toCredentialDTO(crud.findByUsername(username));
    }

}
