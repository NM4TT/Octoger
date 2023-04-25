package com.nmatute.octoger.typemanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.typemanagement.persistence.entity.Credential;

/**
 * Clase para CRUD y custom queries de Credenciales.
 * 
 * @author NM4TT
 */
public interface ICredentialCrudRepository extends CrudRepository<Credential,Long>{
    Credential findByUsername(String username);
}
