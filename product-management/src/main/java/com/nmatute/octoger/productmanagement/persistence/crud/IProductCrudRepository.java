package com.nmatute.octoger.productmanagement.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.productmanagement.persistence.entity.Product;
import com.nmatute.octoger.productmanagement.persistence.entity.ProductCollection;

/**
 * Clase para CRUD y custom queries de Productos.
 * 
 * @author NM4TT
 */
public interface IProductCrudRepository extends CrudRepository<Product,Integer>{
    
    @Query("SELECT p FROM Product p WHERE p.isAvailable = :availability")
    List<Product> findByAvailability(@Param("availability")boolean availability);

    @Query("SELECT p FROM Product p WHERE p.productCollection = :productCollection")
    List<Product> findByCollection(@Param("productCollection") ProductCollection productCollection);
}
