package com.nmatute.octoger.typemanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.typemanagement.persistence.entity.Credential;

public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    Credential findByUsername(String username);
}
