package com.nmatute.octoger.productmanagement.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nmatute.octoger.productmanagement.persistence.entity.ProductCollection;
import com.nmatute.octoger.productmanagement.persistence.entity.User;

/**
 * Clase para CRUD y custom queries de Coleccion de Productos.
 * 
 * @author NM4TT
 */
public interface IProductCollectionCrudRepository extends CrudRepository<ProductCollection,Integer> {
    @Query("SELECT pc FROM ProductCollection pc WHERE pc.user = :user")
    List<ProductCollection> findByUser(@Param("user") User user);

    List<ProductCollection> findByProvider(String provider);
}
