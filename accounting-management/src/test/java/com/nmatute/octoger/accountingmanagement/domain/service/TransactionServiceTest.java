package com.nmatute.octoger.accountingmanagement.domain.service;
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
import com.nmatute.octoger.accountingmanagement.persistence.repository.TransactionRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class TransactionServiceTest {
    
    @Mock
    private TransactionRepository repo;
    @InjectMocks
    private TransactionService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        TransactionDTO transaction = new TransactionDTO();
        transaction.setId(1);
        when(service.getById(1)).thenReturn(transaction);

        TransactionDTO transactionFound = service.getById(1);

        assertEquals(transaction, transactionFound);
    }

    @Test
    void getAll(){
        TransactionDTO transaction = new TransactionDTO();
        when(service.getAll()).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = service.getAll();

        assertNotNull(transactions);
    }

    @Test
    void getByType(){
        TransactionDTO transaction = new TransactionDTO();
        TypeDTO type = new TypeDTO();
        when(service.getByType(type)).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = service.getByType(type);

        assertNotNull(transactions);
    }

    @Test
    void getByDateRange(){
        TransactionDTO transaction = new TransactionDTO();
        Date from = new Date();
        Date to = new Date();
        when(service.getByDateRange(from, to)).thenReturn(List.of(transaction));

        List<TransactionDTO> transactions = service.getByDateRange(from, to);

        assertNotNull(transactions);
    }

    @Test
    void save(){
        TransactionDTO transaction = new TransactionDTO();
        when(service.save(transaction)).thenReturn(transaction);

        TransactionDTO savedTransaction = service.save(transaction);

        assertEquals(transaction, savedTransaction);
    }

}