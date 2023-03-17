package com.nmatute.octoger.usermanagement.persistence.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nmatute.octoger.usermanagement.persistence.entity.User;

public interface IUserCrudRepository extends CrudRepository<User, Integer>{
    List<User> findByTypeWhereTypeLike(String type); 
}
