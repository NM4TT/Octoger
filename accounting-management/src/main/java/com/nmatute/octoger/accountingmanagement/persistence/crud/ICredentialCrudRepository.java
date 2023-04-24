package com.nmatute.octoger.accountingmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.accountingmanagement.persistence.entity.Credential;

/**
 * CRUD y custom query methods de Credenciales.
 * 
 * @author NM4TT
 */
public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    Credential findByUsername(String username);
}
