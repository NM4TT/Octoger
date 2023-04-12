package com.nmatute.octoger.productmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.CredentialMapper;

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
