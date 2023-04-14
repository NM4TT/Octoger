package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.IProductOperationRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IProductOperationCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductOperationMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TransactionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductOperationRepository implements IProductOperationRepository{
    
    private final IProductOperationCrudRepository crud;
    private final ProductOperationMapper mapper;
    private final ProductCollectionMapper collectionMapper;
    private final TypeMapper typeMapper;
    private final UserMapper userMapper;
    private final TransactionMapper transactionMapper;
    
    @Override
    public Optional<ProductOperationDTO> getById(int id) {
        return Optional.of(mapper.toProductOperationDTO(crud.findById(id).get()));
    }

    @Override
    public Optional<List<ProductOperationDTO>> getByCollection(ProductCollectionDTO collection) {
        return Optional.of(mapper.toProductOperationDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection)).get()));
    }

    @Override
    public Optional<List<ProductOperationDTO>> getByType(TypeDTO type) {
        return Optional.of(mapper.toProductOperationDTOs(crud.findByType(typeMapper.toType(type)).get()));
    }

    @Override
    public Optional<List<ProductOperationDTO>> getByResponsible(UserDTO user) {
        return Optional.of(mapper.toProductOperationDTOs(crud.findByResponsible(userMapper.toUser(user)).get()));
    }

    @Override
    public Optional<List<ProductOperationDTO>> getByTransaction(TransactionDTO transaction) {
        return Optional.of(mapper.toProductOperationDTOs(crud.findByTransaction(transactionMapper.toTransaction(transaction)).get()));
    }

    @Override
    public Optional<List<ProductOperationDTO>> getByDateRange(Date from, Date to) {
        return Optional.of(mapper.toProductOperationDTOs(crud.findByDateRange(from, to).get()));
    }

    @Override
    public ProductOperationDTO save(ProductOperationDTO productOperation) {
        ProductOperation po = mapper.toProductOperation(productOperation);
        return mapper.toProductOperationDTO(crud.save(po));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
    
}
