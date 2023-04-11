package com.nmatute.octoger.usermanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.ICredentialRepository;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO;
import com.nmatute.octoger.usermanagement.domain.dto.CredentialDTO.Role;
import com.nmatute.octoger.usermanagement.persistence.crud.ICredentialCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.persistence.mapper.CredentialMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CredentialRepository implements ICredentialRepository{
    
    private final ICredentialCrudRepository crud;
    private final IUserCrudRepository userCrud;
    private final CredentialMapper mapper;

    @Override
    public CredentialDTO get(int userId) {
        CredentialDTO credential = mapper
        .toCredentialDTO(crud.findById(crud.findIdByUserId(userId)).get());
        credential.setRole((getUserType(userId).endsWith("00") ? Role.ADMIN : Role.REGULAR));
        return credential;
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
    
    public int findIdByUserId(int userId){
        return crud.findIdByUserId(userId);
    }

    public CredentialDTO findUserByUsername(String username){
        return mapper.toCredentialDTO(crud.findByUsername(username));
    }

    public String getUserType(int userId){
        return userCrud.getUserType(userId);
    }

}
