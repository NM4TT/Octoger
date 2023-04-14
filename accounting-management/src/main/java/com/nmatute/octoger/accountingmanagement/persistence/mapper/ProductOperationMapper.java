package com.nmatute.octoger.accountingmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.persistence.entity.ProductOperation;

@Mapper(componentModel = "spring")
public interface ProductOperationMapper {

    ProductOperationDTO toProductOperationDTO(ProductOperation productOperation);

    ProductOperation toProductOperation(ProductOperationDTO productOperation);

    List<ProductOperationDTO> toProductOperationDTOs(List<ProductOperation> productOperations);

    List<ProductOperation> toProductOperations(List<ProductOperationDTO> productOperations);
    
}
