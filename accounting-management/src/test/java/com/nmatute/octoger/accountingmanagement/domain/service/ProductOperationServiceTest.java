package com.nmatute.octoger.accountingmanagement.domain.service;

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
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductOperationRepository;

/**
 * Clase para testear Servicio de Operaciones de Producto.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ProductOperationServiceTest {
    
    @Mock
    private ProductOperationRepository repo;
    @InjectMocks
    private ProductOperationService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        ProductOperationDTO operation = new ProductOperationDTO();
        operation.setId(1);
        when(service.getById(1)).thenReturn(operation);

        ProductOperationDTO operationFound = service.getById(1);
        
        assertEquals(operation, operationFound);
    }

    @Test
    void getAll(){
        ProductOperationDTO operation = new ProductOperationDTO();
        when(service.getAll()).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = service.getAll();

        assertNotNull(operations);
    }

    @Test
    void getByCollection(){
        ProductOperationDTO operation = new ProductOperationDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        operation.setCollection(collection);
        when(service.getByCollection(collection)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = service.getByCollection(collection);

        assertNotNull(operations);
    }

    @Test
    void getByType(){
        ProductOperationDTO operation = new ProductOperationDTO();
        TypeDTO type = new TypeDTO();
        operation.setType(type);
        when(service.getByType(type)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = service.getByType(type);

        assertNotNull(operations);
    }

    @Test
    void getByResponsible(){
        ProductOperationDTO operation = new ProductOperationDTO();
        UserDTO user = new UserDTO();
        operation.setUser(user);
        when(service.getByResponsible(user)).thenReturn(List.of(operation));

        List<ProductOperationDTO> operations = service.getByResponsible(user);

        assertNotNull(operations);
    }

    @Test
    void getByTransaction(){
        ProductOperationDTO operation = new ProductOperationDTO();
        TransactionDTO transaction = new TransactionDTO();
        operation.setTransaction(transaction);
        when(service.getByTransaction(transaction)).thenReturn(operation);

        ProductOperationDTO operationFound = service.getByTransaction(transaction);

        assertNotNull(operationFound);
    }

    @Test
    void getByDateRange(){
        ProductOperationDTO sell = new ProductOperationDTO();
        Date from = new Date();
        Date to = new Date();
        when(service.getByDateRange(from, to)).thenReturn(List.of(sell));

        List<ProductOperationDTO> transactions = service.getByDateRange(from, to);

        assertNotNull(transactions);
    }

    @Test
    void save(){
        ProductOperationDTO operation = new ProductOperationDTO();
        when(service.save(operation)).thenReturn(operation);

        ProductOperationDTO savedOperation = service.save(operation);

        assertEquals(operation, savedOperation);
    }

}
