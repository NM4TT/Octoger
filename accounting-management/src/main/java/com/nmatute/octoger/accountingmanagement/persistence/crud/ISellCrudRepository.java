package com.nmatute.octoger.accountingmanagement.persistence.crud;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductCollection;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Sell;
import com.nmatute.octoger.accountingmanagement.persistence.entity.User;

public interface ISellCrudRepository extends CrudRepository<Sell, Integer>{
    
    Optional<List<Sell>> findByCollection(ProductCollection collection);

    Optional<List<Sell>> findByUser(User user);

    Optional<List<Sell>> findByProductOperation(ProductOperation productOperation);

    @Query("SELECT po from ProductOperation po WHERE po.date >= :from AND <= :to")
    Optional<List<Sell>> findByDateRange(@Param("from") Date from, @Param("to") Date to);

}
