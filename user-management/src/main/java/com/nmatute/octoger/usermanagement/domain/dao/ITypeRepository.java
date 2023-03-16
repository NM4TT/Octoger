package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nmatute.octoger.usermanagement.persistence.entity.Type;

@Component
public interface ITypeRepository {
 
    List<Type> getAll();
    
}
