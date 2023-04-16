package com.nmatute.octoger.accountingmanagement.persistence.crud;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.accountingmanagement.persistence.entity.Transaction;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Type;

public interface ITransactionCrudRepository extends CrudRepository<Transaction, Integer>{
    
    Optional<List<Transaction>> findByType(Type type);

    @Query("SELECT po from ProductOperation po WHERE po.date BETWEEN :from AND :to")
    Optional<List<Transaction>> findByDateRange(@Param("from") Date from, @Param("to") Date to);

}
