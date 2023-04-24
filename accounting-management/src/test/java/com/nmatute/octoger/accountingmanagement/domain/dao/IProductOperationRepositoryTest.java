package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IProductOperationCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductOperationMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TransactionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductOperationRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class IProductOperationRepositoryTest {
    
    @Mock
    private IProductOperationCrudRepository crud;
    @Mock
    private ProductOperationMapper mapper;
    @Mock
    private ProductCollectionMapper collectionMapper;
    @Mock
    private TransactionMapper transactionMapper;
    @Mock
    private UserMapper userMapper;
    @Mock
    private TypeMapper typeMapper;
    @InjectMocks
    private ProductOperationRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        ProductOperationDTO operation = new ProductOperationDTO();
        operation.setId(1);
        when(repo.getById(1)).thenReturn(operation);

        ProductOperationDTO operationFound = repo.getById(1);
        
        assertEquals(operation, operationFound);
    }

    @Test
    void getAll(){
        ProductOperationDTO operation = new ProductOperationDTO();
        when(repo.getAll()).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = repo.getAll();

        assertNotNull(operations);
    }

    @Test
    void getByCollection(){
        ProductOperationDTO operation = new ProductOperationDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        operation.setCollection(collection);
        when(repo.getByCollection(collection)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = repo.getByCollection(collection);

        assertNotNull(operations);
    }

    @Test
    void getByType(){
        ProductOperationDTO operation = new ProductOperationDTO();
        TypeDTO type = new TypeDTO();
        operation.setType(type);
        when(repo.getByType(type)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = repo.getByType(type);

        assertNotNull(operations);
    }

    @Test
    void getByResponsible(){
        ProductOperationDTO operation = new ProductOperationDTO();
        UserDTO user = new UserDTO();
        operation.setUser(user);
        when(repo.getByResponsible(user)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = repo.getByResponsible(user);

        assertNotNull(operations);
    }

    @Test
    void getByTransaction(){
        ProductOperationDTO operation = new ProductOperationDTO();
        TransactionDTO transaction = new TransactionDTO();
        operation.setTransaction(transaction);
        when(repo.getByTransaction(transaction)).thenReturn(operation);

        ProductOperationDTO operationFound = repo.getByTransaction(transaction);

        assertNotNull(operationFound);
    }

    @Test
    void getByDateRange(){
        ProductOperationDTO sell = new ProductOperationDTO();
        Date from = new Date();
        Date to = new Date();
        when(repo.getByDateRange(from, to)).thenReturn(List.of(sell));

        List<ProductOperationDTO> transactions = repo.getByDateRange(from, to);

        assertNotNull(transactions);
    }

    @Test
    void save(){
        ProductOperationDTO operation = new ProductOperationDTO();
        when(repo.save(operation)).thenReturn(operation);

        ProductOperationDTO savedOperation = repo.save(operation);

        assertEquals(operation, savedOperation);
    }

}