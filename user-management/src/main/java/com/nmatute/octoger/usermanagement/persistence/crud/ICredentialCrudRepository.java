package com.nmatute.octoger.usermanagement.persistence.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.usermanagement.persistence.entity.Credential;

public interface ICredentialCrudRepository extends CrudRepository<Credential,Integer>{
    @Query("SELECT id FROM credential c WHERE c.user_id = :userId")
    int findIdByUserId(@Param("userId") int userId);
}
