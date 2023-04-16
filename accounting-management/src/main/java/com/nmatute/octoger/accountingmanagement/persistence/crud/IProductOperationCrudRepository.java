package com.nmatute.octoger.accountingmanagement.persistence.crud;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductCollection;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Transaction;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Type;
import com.nmatute.octoger.accountingmanagement.persistence.entity.User;

public interface IProductOperationCrudRepository extends CrudRepository<ProductOperation, Integer> {
    
    Optional<List<ProductOperation>> findByCollection(ProductCollection collection);

    Optional<List<ProductOperation>> findByType(Type type);

    Optional<List<ProductOperation>> findByUser(User user);

    Optional<ProductOperation> findByTransaction(Transaction transaction);

    @Query("SELECT po from ProductOperation po WHERE po.date BETWEEN :from AND :to")
    Optional<List<ProductOperation>> findByDateRange(@Param("from") Date from, @Param("to") Date to);

}
