package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nmatute.octoger.usermanagement.persistence.entity.Type;

@Component
public interface ITypeRepository {
 
    List<Type> getAll();
    Optional<Type> getById(int id);
    
    Optional<Type> getByIdentifier(String identifier);
    
    Type save(Type type);

    Type delete(int id);
}
