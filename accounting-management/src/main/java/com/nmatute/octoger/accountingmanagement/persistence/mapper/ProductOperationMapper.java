package com.nmatute.octoger.accountingmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;

/**
 * Clase para Mapper de Operaciones de Producto.
 * 
 * @author NM4TT
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductCollectionMapper.class, TypeMapper.class, TransactionMapper.class})
public interface ProductOperationMapper {

    ProductOperationDTO toProductOperationDTO(ProductOperation productOperation);

    ProductOperation toProductOperation(ProductOperationDTO productOperation);

    List<ProductOperationDTO> toProductOperationDTOs(List<ProductOperation> productOperations);

    List<ProductOperation> toProductOperations(List<ProductOperationDTO> productOperations);
    
}
