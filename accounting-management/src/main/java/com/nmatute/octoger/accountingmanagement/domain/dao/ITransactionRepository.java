package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Date;
import java.util.List;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

public interface ITransactionRepository {
    
    TransactionDTO getById(int id);

    List<TransactionDTO> getAll();

    List<TransactionDTO> getByType(TypeDTO type);

    List<TransactionDTO> getByDateRange(Date from, Date to);

    TransactionDTO save(TransactionDTO transaction);

    void delete(int id);

}
