package com.nmatute.octoger.productmanagement.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.IProductRepository;
import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IProductCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.Product;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Repositorio de Productos.
 * 
 * @author NM4TT
 */
@Repository
@RequiredArgsConstructor
public class ProductRepository implements IProductRepository {
    
    private final IProductCrudRepository crud;
    private final ProductMapper mapper;
    private final ProductCollectionMapper collectionMapper;
    
    @Override
    public ProductDTO getById(int id) {
        return mapper.toProductDTO(crud.findById(id).orElse(null));
    }
    @Override
    public List<ProductDTO> getByCollection(ProductCollectionDTO collection) {
        return mapper.toProductDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection)));
    }
    @Override
    public List<ProductDTO> getAvailables() {
        return mapper.toProductDTOs(crud.findByAvailability(true));
    }
    @Override
    public List<ProductDTO> getNonAvailables() {
        return mapper.toProductDTOs(crud.findByAvailability(false));
    }
    @Override
    public ProductDTO save(ProductDTO product) {
        Product p = mapper.toProduct(product);
        return mapper.toProductDTO(crud.save(p));
    }
    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.toProductDTOs((List<Product>) crud.findAll());
    }
}
