package com.nmatute.octoger.accountingmanagement.persistence.crud;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductCollection;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Sell;
import com.nmatute.octoger.accountingmanagement.persistence.entity.User;

public interface ISellCrudRepository extends CrudRepository<Sell, Integer>{
    
    List<Sell> findByCollection(ProductCollection collection);

    List<Sell> findByUser(User user);

    Sell findByProductOperation(ProductOperation productOperation);

    @Query("SELECT s from Sell s WHERE s.date BETWEEN :from AND :to")
    List<Sell> findByDateRange(@Param("from") Date from, @Param("to") Date to);

}
