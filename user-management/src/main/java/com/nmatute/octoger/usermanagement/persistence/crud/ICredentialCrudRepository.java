package com.nmatute.octoger.usermanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.usermanagement.persistence.entity.Credential;

public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    
}
