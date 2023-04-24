package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.ISellRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ISellCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Sell;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductOperationMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.SellMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SellRepository implements ISellRepository{

    private final ISellCrudRepository crud;
    private final SellMapper mapper;
    private final ProductCollectionMapper collectionMapper;
    private final UserMapper userMapper;
    private final ProductOperationMapper productOperationMapper;

    @Override
    public SellDTO getById(int id) {
        SellDTO sell = mapper.toSellDTO(crud.findById(id).orElse(null));
        return sell;
    }

    @Override
    public List<SellDTO> getByCollection(ProductCollectionDTO collection) {
        return mapper.toSellDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection)));
    }

    @Override
    public List<SellDTO> getByResponsible(UserDTO user) {
        return mapper.toSellDTOs(crud.findByUser(userMapper.toUser(user)));
    }

    @Override
    public SellDTO getByProductOperation(ProductOperationDTO productOperation) {
        return mapper.toSellDTO(crud.findByProductOperation(productOperationMapper.toProductOperation(productOperation)));
    }

    @Override
    public List<SellDTO> getByDateRange(Date from, Date to) {
        return mapper.toSellDTOs(crud.findByDateRange(from, to));
    }

    @Override
    public SellDTO save(SellDTO sell) {
        Sell s = mapper.toSell(sell);
        return mapper.toSellDTO(crud.save(s));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }

    @Override
    public List<SellDTO> getAll() {
        return mapper.toSellDTOs((List<Sell>) crud.findAll());
    }
    


}
