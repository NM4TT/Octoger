package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

public interface ITransactionRepository {
    
    Optional<TransactionDTO> getById(int id);

    Optional<List<TransactionDTO>> getByType(TypeDTO type);

    Optional<List<TransactionDTO>> getByDateRange(Date from, Date to);

    TransactionDTO save(TransactionDTO transaction);

    void delete(int id);

}
