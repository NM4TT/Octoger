package com.nmatute.octoger.productmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.User;

/**
 * Clase para CRUD y custom queries de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserCrudRepository extends CrudRepository<User, Integer>{
   
}
