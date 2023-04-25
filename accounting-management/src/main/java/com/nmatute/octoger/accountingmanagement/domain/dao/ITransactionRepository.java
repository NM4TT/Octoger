package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Date;
import java.util.List;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

/**
 * DAO de Transacciones.
 * 
 * @author NM4TT
 */
public interface ITransactionRepository {
    
    TransactionDTO getById(long id);

    List<TransactionDTO> getAll();

    List<TransactionDTO> getByType(TypeDTO type);

    List<TransactionDTO> getByDateRange(Date from, Date to);

    TransactionDTO save(TransactionDTO transaction);

    void delete(long id);

}
