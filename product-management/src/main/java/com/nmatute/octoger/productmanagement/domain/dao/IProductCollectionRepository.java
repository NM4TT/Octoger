package com.nmatute.octoger.productmanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;

public interface IProductCollectionRepository {
    ProductCollectionDTO getById(int id);
    Optional<List<ProductCollectionDTO>> getByResponsible(UserDTO user);
    Optional<List<ProductCollectionDTO>> getByProvider(String provider);
    ProductCollectionDTO save(ProductCollectionDTO productCollection);
    void delete(int id);
}
