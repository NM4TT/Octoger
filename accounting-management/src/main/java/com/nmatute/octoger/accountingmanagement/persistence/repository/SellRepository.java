package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Optional<SellDTO> getById(int id) {
        return Optional.of(mapper.toSellDTO(crud.findById(id).get()));
    }

    @Override
    public Optional<List<SellDTO>> getByCollection(ProductCollectionDTO collection) {
        return Optional.of(mapper.toSellDTOs(crud.findByCollection(collectionMapper.toProductCollection(collection)).get()));
    }

    @Override
    public Optional<List<SellDTO>> getByResponsible(UserDTO user) {
        return Optional.of(mapper.toSellDTOs(crud.findByUser(userMapper.toUser(user)).get()));
    }

    @Override
    public Optional<SellDTO> getByProductOperation(ProductOperationDTO productOperation) {
        return Optional.of(mapper.toSellDTO(crud.findByProductOperation(productOperationMapper.toProductOperation(productOperation)).get()));
    }

    @Override
    public Optional<List<SellDTO>> getByDateRange(Date from, Date to) {
        return Optional.of(mapper.toSellDTOs(crud.findByDateRange(from, to).get()));
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
    public Optional<List<SellDTO>> getAll() {
        return Optional.of(mapper.toSellDTOs((List<Sell>) crud.findAll()));
    }
    


}
