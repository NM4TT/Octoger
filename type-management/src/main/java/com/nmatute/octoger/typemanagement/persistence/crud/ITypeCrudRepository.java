package com.nmatute.octoger.typemanagement.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.typemanagement.persistence.entity.Type;

public interface ITypeCrudRepository extends CrudRepository<Type, Integer>{
    List<Type> findByIdentifierWhereIdentifierLike(String identifier);
}
