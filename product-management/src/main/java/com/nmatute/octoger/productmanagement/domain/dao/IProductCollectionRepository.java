package com.nmatute.octoger.productmanagement.domain.dao;

import java.util.List;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;

/**
 * DAO de Coleccion de Productos.
 * 
 * @author NM4TT
 */
public interface IProductCollectionRepository {
    ProductCollectionDTO getById(long id);
    List<ProductCollectionDTO> getByResponsible(UserDTO user);
    List<ProductCollectionDTO> getByProvider(String provider);
    List<ProductCollectionDTO> getAll();
    ProductCollectionDTO save(ProductCollectionDTO productCollection);
    void delete(long id);
}
