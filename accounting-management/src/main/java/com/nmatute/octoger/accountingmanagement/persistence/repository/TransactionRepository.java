package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.ITransactionRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ITransactionCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Transaction;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TransactionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Repositorio de Transacciones.
 * 
 * @author NM4TT
 */
@Repository
@RequiredArgsConstructor
public class TransactionRepository implements ITransactionRepository{
    
    private final ITransactionCrudRepository crud;
    private final TransactionMapper mapper;
    private final TypeMapper typeMapper;
    
    @Override
    public TransactionDTO getById(int id) {
        TransactionDTO transaction = mapper.toTransactionDTO(crud.findById(id).orElse(null));
        return transaction;
    }

    @Override
    public List<TransactionDTO> getByType(TypeDTO type) {
        return mapper.toTransactionDTOs(crud.findByType(typeMapper.toType(type)));
    }

    @Override
    public List<TransactionDTO> getByDateRange(Date from, Date to) {
        return mapper.toTransactionDTOs(crud.findByDateRange(from, to));
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
    public List<TransactionDTO> getAll() {
        return mapper.toTransactionDTOs((List<Transaction>) crud.findAll());
    }
    
}
