package com.nmatute.octoger.usermanagement.domain.dao;

import org.springframework.stereotype.Component;

import com.nmatute.octoger.usermanagement.persistence.entity.Credential;

@Component
public interface ICredentialRepository {
    
    Credential get(int id, IdType type);

    String getUsername();

    String getPassword();

    Credential save(Credential credential);

}


enum IdType {
    USER,
    CREDENTIAL
}
