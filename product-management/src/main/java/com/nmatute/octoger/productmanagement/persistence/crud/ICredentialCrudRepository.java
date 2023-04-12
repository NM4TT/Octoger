package com.nmatute.octoger.productmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.productmanagement.persistence.entity.Credential;

public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    Credential findByUsername(String username);
}
