package com.nmatute.octoger.usermanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.ICredentialRepository;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.persistence.mapper.CredentialMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CredentialRepository implements ICredentialRepository{
    private ICredentialCrudRepository crud;
    private CredentialMapper mapper;

    @Override
    public CredentialDTO get(int userId) {
        return mapper.toCredentialDTO(crud.findById(crud.findIdByUserId(userId))
        .get());
    }

    @Override
    public String getUsername(int userId) {
        return crud.findById(crud.findIdByUserId(userId)).get().getUsername();
    }

    @Override
    public String getPassword(int userId) {
        return crud.findById(crud.findIdByUserId(userId)).get().getPassword();
    }

    @Override
    public CredentialDTO save(CredentialDTO credential) {
        Credential c = crud.save(mapper.toCredential(credential));
        return mapper.toCredentialDTO(c);
    }
    
}
