package com.nmatute.octoger.accountingmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.User;

/**
 * CRUD y custom query methods de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserCrudRepository extends CrudRepository<User, Long>{
   
}
