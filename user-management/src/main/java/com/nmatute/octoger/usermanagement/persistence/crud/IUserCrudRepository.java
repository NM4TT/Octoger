package com.nmatute.octoger.usermanagement.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.usermanagement.persistence.entity.Type;
import com.nmatute.octoger.usermanagement.persistence.entity.User;

public interface IUserCrudRepository extends CrudRepository<User, Integer>{
    List<User> findByType(Type type); 
    
    @Query("SELECT count(*) FROM User WHERE id = :userId")
    int findUser(@Param("userId") int userId);

    @Query("SELECT type FROM User WHERE type = :userId")
    String getUserType(@Param("userId") int userId);
}
