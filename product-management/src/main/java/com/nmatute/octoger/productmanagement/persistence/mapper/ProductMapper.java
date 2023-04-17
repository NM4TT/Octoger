package com.nmatute.octoger.productmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.entity.Product;

@Mapper(componentModel = "spring", uses = ProductCollectionMapper.class)
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO product);
    List<ProductDTO> toProductDTOs(List<Product> products);
    List<Product> toProducts(List<ProductDTO> products);

}
