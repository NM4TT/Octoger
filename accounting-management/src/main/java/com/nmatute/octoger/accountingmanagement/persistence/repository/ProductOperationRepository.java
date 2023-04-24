package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;

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

/**
 * Clase para Repositorio de Operaciones de Producto.
 * 
 * @author NM4TT
 */
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
    public ProductOperationDTO getById(int id) {
        ProductOperationDTO operation = mapper.toProductOperationDTO(crud.findById(id).orElse(null));
        return operation;
    }

    @Override
    public List<ProductOperationDTO> getByCollection(ProductCollectionDTO collection) {
        return mapper.toProductOperationDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection)));
    }

    @Override
    public List<ProductOperationDTO> getByType(TypeDTO type) {
        return mapper.toProductOperationDTOs(crud.findByType(typeMapper.toType(type)));
    }

    @Override
    public List<ProductOperationDTO> getByResponsible(UserDTO user) {
        return mapper.toProductOperationDTOs(crud.findByUser(userMapper.toUser(user)));
    }

    @Override
    public ProductOperationDTO getByTransaction(TransactionDTO transaction) {
        return mapper.toProductOperationDTO(crud.findByTransaction(transactionMapper.toTransaction(transaction)));
    }

    @Override
    public List<ProductOperationDTO> getByDateRange(Date from, Date to) {
        return mapper.toProductOperationDTOs(crud.findByDateRange(from, to));
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

    @Override
    public List<ProductOperationDTO> getAll() {
        return mapper.toProductOperationDTOs((List<ProductOperation>)crud.findAll());
    }
    
}
