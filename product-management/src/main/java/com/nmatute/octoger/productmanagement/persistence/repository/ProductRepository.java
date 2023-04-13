package com.nmatute.octoger.productmanagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.IProductRepository;
import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IProductCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.Product;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductRepository implements IProductRepository {
    
    private final IProductCrudRepository crud;
    private final ProductMapper mapper;
    private final ProductCollectionMapper collectionMapper;
    
    @Override
    public ProductDTO getById(int id) {
        return mapper.toProductDTO(crud.findById(id).orElse(null));
    }
    @Override
    public Optional<List<ProductDTO>> getByCollection(ProductCollectionDTO collection) {
        return Optional.of(mapper.toProductDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection))));
    }
    @Override
    public Optional<List<ProductDTO>> getAvailables() {
        return Optional.of(mapper.toProductDTOs(crud.findByAvailability(true)));
    }
    @Override
    public Optional<List<ProductDTO>> getNonAvailables() {
        return Optional.of(mapper.toProductDTOs(crud.findByAvailability(false)));
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
    public Optional<List<ProductDTO>> getAll() {
        return Optional.of(mapper.toProductDTOs((List<Product>) crud.findAll()));
    }
}
