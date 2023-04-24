package com.nmatute.octoger.productmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.Type;

/**
 * Clase para CRUD y custom queries de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeCrudRepository extends CrudRepository<Type,Integer>{
    Type findByIdentifier(String identifier);
}
