package com.nmatute.octoger.productmanagement.domain.dao;

import java.util.List;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;

/**
 * DAO de Productos.
 * 
 * @author NM4TT
 */
public interface IProductRepository {
    ProductDTO getById(int id);
    List<ProductDTO> getByCollection(ProductCollectionDTO collection);
    List<ProductDTO> getAvailables();
    List<ProductDTO> getNonAvailables();
    ProductDTO save(ProductDTO product);
    List<ProductDTO> getAll();
    void delete(int id);
}
