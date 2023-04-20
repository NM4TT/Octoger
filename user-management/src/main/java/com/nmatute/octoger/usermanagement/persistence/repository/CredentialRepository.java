package com.nmatute.octoger.usermanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.ICredentialRepository;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.persistence.mapper.CredentialMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CredentialRepository implements ICredentialRepository{
    
    private final ICredentialCrudRepository crud;
    private final IUserCrudRepository userCrud;
    private final CredentialMapper mapper;

    @Override
    public CredentialDTO save(CredentialDTO credential) {
        Credential c = crud.save(mapper.toCredential(credential));
        return mapper.toCredentialDTO(c);
    }
    
    @Override
    public int findIdByUsername(String username){
        return crud.findByUsername(username).getId();
    }

    @Override
    public CredentialDTO findByUsername(String username){
        CredentialDTO credential = mapper
        .toCredentialDTO(crud.findByUsername(username));
        return credential;
    }

    @Override
    public String getUserType(int userId){
        return userCrud.getUserType(userId);
    }

}
