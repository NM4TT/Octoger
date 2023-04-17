package com.nmatute.octoger.productmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.persistence.entity.ProductCollection;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ProductCollectionMapper {

    ProductCollectionDTO toProductCollectionDTO(ProductCollection collection);

    @InheritInverseConfiguration
    ProductCollection toProductCollection(ProductCollectionDTO collection);

    List<ProductCollectionDTO> toProductCollectionDTOs(List<ProductCollection> collection);
    List<ProductCollection> toProductCollections(List<ProductCollectionDTO> collection);

}
