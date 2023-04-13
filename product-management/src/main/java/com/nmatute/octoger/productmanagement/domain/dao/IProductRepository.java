package com.nmatute.octoger.productmanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;

public interface IProductRepository {
    ProductDTO getById(int id);
    Optional<List<ProductDTO>> getByCollection(ProductCollectionDTO collection);
    Optional<List<ProductDTO>> getAvailables();
    Optional<List<ProductDTO>> getNonAvailables();
    ProductDTO save(ProductDTO product);
    void delete(int id);
}
