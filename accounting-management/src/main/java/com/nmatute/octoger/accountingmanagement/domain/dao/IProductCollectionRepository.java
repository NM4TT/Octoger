package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;

/**
 * DAO de Coleccion de Productos.
 * 
 * @author NM4TT
 */
public interface IProductCollectionRepository {
    ProductCollectionDTO getById(long id);
}
