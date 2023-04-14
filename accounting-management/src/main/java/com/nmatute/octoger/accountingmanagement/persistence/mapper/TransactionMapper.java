package com.nmatute.octoger.accountingmanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.persistence.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    
    TransactionDTO toTransactionDTO(Transaction transaction);

    Transaction toTransaction(TransactionDTO transaction);

    List<TransactionDTO> toTransactionDTOs(List<Transaction> transactions);

    List<Transaction> toTransactions(List<TransactionDTO> transactions);

}
