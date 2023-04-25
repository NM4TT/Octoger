package com.nmatute.octoger.accountingmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Type;

/**
 * CRUD y custom query methods de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeCrudRepository extends CrudRepository<Type,Long>{
    Type findByIdentifier(String identifier);
}
