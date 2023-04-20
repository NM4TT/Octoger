package com.nmatute.octoger.usermanagement.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.usermanagement.persistence.entity.Credential;

/**
 * CRUD y custom query methods de Credenciales.
 * 
 * @author NM4TT
 */
public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    @Query("SELECT c FROM Credential c WHERE c.username = :username")
    Credential findByUsername(@Param("username")String username);
}
