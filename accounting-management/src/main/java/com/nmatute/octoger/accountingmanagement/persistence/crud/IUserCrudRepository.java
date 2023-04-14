package com.nmatute.octoger.accountingmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.User;

public interface IUserCrudRepository extends CrudRepository<User, Integer>{
   
}
