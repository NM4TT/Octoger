package com.nmatute.octoger.typemanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.nmatute.octoger.typemanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.typemanagement.persistence.entity.Type;


@Component
@Mapper(componentModel = "spring")
public interface TypeMapper {
    
    TypeDTO toTypeDTO(Type Type);

    Type toType(TypeDTO typeDTO);

    List<TypeDTO> toTypeDTOs(List<Type> Types);

    List<Type> toTypes(List<TypeDTO> TypeDTOs);

}
