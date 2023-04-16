package com.nmatute.octoger.accountingmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.IProductCollectionRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IProductCollectionCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductCollectionRepository implements IProductCollectionRepository{
    
    private final IProductCollectionCrudRepository crud;
    private final ProductCollectionMapper mapper;

    @Override
    public ProductCollectionDTO getById(int id) {
        return mapper.toProductCollectionDTO(crud.findById(id).orElse(null));
    }
    
}