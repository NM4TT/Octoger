package com.nmatute.octoger.accountingmanagement.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductCollection;

/**
 * CRUD y custom query methods de Coleccion de Productos.
 * 
 * @author NM4TT
 */
public interface IProductCollectionCrudRepository extends CrudRepository<ProductCollection,Integer> {

}
