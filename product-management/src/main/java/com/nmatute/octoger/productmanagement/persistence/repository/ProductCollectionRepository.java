package com.nmatute.octoger.productmanagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.IProductCollectionRepository;
import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IProductCollectionCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.ProductCollection;
import com.nmatute.octoger.productmanagement.persistence.entity.User;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.productmanagement.persistence.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductCollectionRepository implements IProductCollectionRepository{
    
    private final IProductCollectionCrudRepository crud;
    private final ProductCollectionMapper mapper;
    private final UserMapper userMapper;

    @Override
    public ProductCollectionDTO getById(int id) {
        return mapper.toProductCollectionDTO(crud.findById(id).orElse(null));
    }
    @Override
    public Optional<List<ProductCollectionDTO>> getByResponsible(UserDTO user) {
        return Optional.of(mapper.toProductCollectionDTOs(crud.findByUser(userMapper.toUser(user))));
    }
    @Override
    public Optional<List<ProductCollectionDTO>> getByProvider(String provider) {
        return Optional.of(mapper.toProductCollectionDTOs(crud.findByProvider(provider)));
    }
    @Override
    public ProductCollectionDTO save(ProductCollectionDTO productCollection) {
        ProductCollection pc = mapper.toProductCollection(productCollection);
        return mapper.toProductCollectionDTO(crud.save(pc));
    }
    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
    public Optional<List<ProductCollectionDTO>> getAll() {
        return Optional.of(mapper.toProductCollectionDTOs((List<ProductCollection>) crud.findAll()));
    }
}