package com.nmatute.octoger.usermanagement.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.usermanagement.persistence.entity.Credential;
import com.nmatute.octoger.usermanagement.persistence.entity.User;

public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    @Query("SELECT id FROM Credential WHERE user = :userId")
    int findIdByUserId(@Param("userId") int userId);

    Credential findByUsername(String username);

}
