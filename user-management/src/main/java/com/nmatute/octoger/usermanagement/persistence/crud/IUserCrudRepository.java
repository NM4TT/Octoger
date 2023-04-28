package com.nmatute.octoger.usermanagement.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.usermanagement.persistence.entity.Type;
import com.nmatute.octoger.usermanagement.persistence.entity.User;

/**
 * CRUD y custom query methods de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserCrudRepository extends CrudRepository<User, Long>{
    List<User> findByType(Type type); 
    
    @Query("SELECT count(*) FROM User WHERE id = :userId")
    int findUser(@Param("userId") long userId);

    @Query("SELECT type FROM User WHERE type = :userId")
    String getUserType(@Param("userId") long userId);
}
