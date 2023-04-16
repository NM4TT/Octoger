package com.nmatute.octoger.accountingmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Sell;

@Mapper(componentModel = "spring", uses = {ProductCollectionMapper.class, UserMapper.class, ProductOperationMapper.class})
public interface SellMapper {
    
    SellDTO toSellDTO(Sell sell);

    Sell toSell(SellDTO sell);

    List<SellDTO> toSellDTOs(List<Sell> sells);

    List<Sell> toSells(List<SellDTO> sells);

}
