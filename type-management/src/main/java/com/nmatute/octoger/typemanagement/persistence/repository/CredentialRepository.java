package com.nmatute.octoger.typemanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.typemanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.typemanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.typemanagement.persistence.mapper.CredentialMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CredentialRepository {
    
    private final ICredentialCrudRepository crud;
    private final CredentialMapper mapper;

    public CredentialDTO findByUsername(String username){
        return mapper.toCredentialDTO(crud.findByUsername(username));
    }

}
