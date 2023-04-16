package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

public interface IProductOperationRepository {
    
    Optional<ProductOperationDTO> getById(int id);

    Optional<List<ProductOperationDTO>> getAll();

    Optional<List<ProductOperationDTO>> getByCollection(ProductCollectionDTO collection);

    Optional<List<ProductOperationDTO>> getByType(TypeDTO type);

    Optional<List<ProductOperationDTO>> getByResponsible(UserDTO user);

    Optional<ProductOperationDTO> getByTransaction(TransactionDTO transaction);

    Optional<List<ProductOperationDTO>> getByDateRange(Date from, Date to);

    ProductOperationDTO save(ProductOperationDTO productOperation);

    void delete(int id);

}
