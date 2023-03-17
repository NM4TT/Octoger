package com.nmatute.octoger.usermanagement.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.usermanagement.persistence.entity.Type;

public interface ITypeCrudRepository extends CrudRepository<Type, Integer>{
    List<Type> findByIdentifierWhereIdentifierLike(String identifier);
}
