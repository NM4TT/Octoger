package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.List;
import java.util.Date;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

/**
 * DAO de Operacion de Productos.
 * 
 * @author NM4TT
 */
public interface IProductOperationRepository {
    
    ProductOperationDTO getById(long id);

    List<ProductOperationDTO> getAll();

    List<ProductOperationDTO> getByCollection(ProductCollectionDTO collection);

    List<ProductOperationDTO> getByType(TypeDTO type);

    List<ProductOperationDTO> getByResponsible(UserDTO user);

    ProductOperationDTO getByTransaction(TransactionDTO transaction);

    List<ProductOperationDTO> getByDateRange(Date from, Date to);

    ProductOperationDTO save(ProductOperationDTO productOperation);

    void delete(long id);

}
