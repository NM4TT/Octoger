package com.nmatute.octoger.typemanagement.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.typemanagement.persistence.entity.Type;

/**
 * Clase para CRUD y custom queries de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeCrudRepository extends CrudRepository<Type,Integer>{
    Type findByIdentifier(String identifier);

    @Query("SELECT t FROM Type t WHERE t.identifier LIKE :prefix%")
    List<Type> findByPrefix(@Param("prefix") String prefix);
}
