package com.nmatute.octoger.productmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.User;

public interface IUserCrudRepository extends CrudRepository<User, Integer>{
   
}
