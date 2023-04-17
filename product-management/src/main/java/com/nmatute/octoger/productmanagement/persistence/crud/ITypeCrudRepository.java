package com.nmatute.octoger.productmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.Type;

public interface ITypeCrudRepository extends CrudRepository<Type,Integer>{
    Type findByIdentifier(String identifier);
}
