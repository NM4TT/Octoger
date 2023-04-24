package com.nmatute.octoger.accountingmanagement.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ITransactionCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TransactionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.accountingmanagement.persistence.repository.TransactionRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ITransactionRepositoryTest {
    
    @Mock
    private ITransactionCrudRepository crud;
    @Mock
    private TransactionMapper mapper;
    @Mock
    private TypeMapper typeMapper;
    @InjectMocks
    private TransactionRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        TransactionDTO transaction = new TransactionDTO();
        transaction.setId(1);
        when(repo.getById(1)).thenReturn(transaction);

        TransactionDTO transactionFound = repo.getById(1);

        assertEquals(transaction, transactionFound);
    }

    @Test
    void getAll(){
        TransactionDTO transaction = new TransactionDTO();
        when(repo.getAll()).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = repo.getAll();

        assertNotNull(transactions);
    }

    @Test
    void getByType(){
        TransactionDTO transaction = new TransactionDTO();
        TypeDTO type = new TypeDTO();
        when(repo.getByType(type)).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = repo.getByType(type);

        assertNotNull(transactions);
    }

    @Test
    void getByDateRange(){
        TransactionDTO transaction = new TransactionDTO();
        Date from = new Date();
        Date to = new Date();
        when(repo.getByDateRange(from, to)).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = repo.getByDateRange(from, to);

        assertNotNull(transactions);
    }

    @Test
    void save(){
        TransactionDTO transaction = new TransactionDTO();
        when(repo.save(transaction)).thenReturn(transaction);

        TransactionDTO savedTransaction = repo.save(transaction);

        assertEquals(transaction, savedTransaction);
    }

}
