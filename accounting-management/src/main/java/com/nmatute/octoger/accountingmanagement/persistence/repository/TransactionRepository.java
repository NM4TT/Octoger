package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.ITransactionRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ITransactionCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Transaction;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TransactionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository implements ITransactionRepository{
    
    private final ITransactionCrudRepository crud;
    private final TransactionMapper mapper;
    private final TypeMapper typeMapper;
    
    @Override
    public Optional<TransactionDTO> getById(int id) {
        return Optional.of(mapper.toTransactionDTO(crud.findById(id).get()));
    }

    @Override
    public Optional<List<TransactionDTO>> getByType(TypeDTO type) {
        return Optional.of(mapper.toTransactionDTOs(crud.findByType(typeMapper.toType(type)).get()));
    }

    @Override
    public Optional<List<TransactionDTO>> getByDateRange(Date from, Date to) {
        return Optional.of(mapper.toTransactionDTOs(crud.findByDateRange(from, to).get()));
    }

    @Override
    public TransactionDTO save(TransactionDTO transaction) {
        Transaction t = mapper.toTransaction(transaction);
        return mapper.toTransactionDTO(crud.save(t));
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }

    @Override
    public Optional<List<TransactionDTO>> getAll() {
        return Optional.of(mapper.toTransactionDTOs((List<Transaction>) crud.findAll()));
    }
    
}
