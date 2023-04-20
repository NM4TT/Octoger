package com.nmatute.octoger.usermanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.Type;

/**
 * CRUD y custom query methods de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeCrudRepository extends CrudRepository<Type,Integer>{
    Type findByIdentifier(String identifier);
}
