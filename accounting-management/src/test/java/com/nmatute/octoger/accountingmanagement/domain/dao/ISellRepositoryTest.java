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

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.ISellCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductOperationMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.SellMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.accountingmanagement.persistence.repository.SellRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ISellRepositoryTest {
    
    @Mock
    private ISellCrudRepository crud;
    @Mock
    private SellMapper mapper;
    @Mock
    private ProductCollectionMapper collectionMapper;
    @Mock
    private ProductOperationMapper operationMapper;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private SellRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        SellDTO sell = new SellDTO();
        sell.setId(1);
        when(repo.getById(1)).thenReturn(sell);

        SellDTO sellFound = repo.getById(1);

        assertEquals(sell, sellFound);
    }

    @Test
    void getAll(){
        SellDTO sell = new SellDTO();
        when(repo.getAll()).thenReturn(List.of(sell));

        List<SellDTO> sells = repo.getAll();

        assertNotNull(sells);
    }

    @Test
    void getByCollection(){
        SellDTO sell = new SellDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        sell.setCollection(collection);
        when(repo.getByCollection(collection)).thenReturn(List.of(sell));

        List<SellDTO> sells = repo.getByCollection(collection);

        assertNotNull(sells);
    }

    @Test
    void getByResponsible(){
        SellDTO sell = new SellDTO();
        UserDTO user = new UserDTO();
        sell.setUser(user);
        when(repo.getByResponsible(user)).thenReturn(List.of(sell));

        List<SellDTO> sells = repo.getByResponsible(user);

        assertNotNull(sells);
    }

    @Test
    void getByProductOperation(){
        SellDTO sell = new SellDTO();
        ProductOperationDTO operation = new ProductOperationDTO();
        sell.setProductOperation(operation);
        when(repo.getByProductOperation(operation)).thenReturn(sell);

        SellDTO sellFound = repo.getByProductOperation(operation);

        assertNotNull(sellFound);
    }

    @Test
    void getByDateRange(){
        SellDTO sell = new SellDTO();
        Date from = new Date();
        Date to = new Date();
        when(repo.getByDateRange(from, to)).thenReturn(List.of(sell));

        List<SellDTO> sells = repo.getByDateRange(from, to);

        assertNotNull(sells);
    }

    @Test
    void save(){
        SellDTO sell = new SellDTO();
        when(repo.save(sell)).thenReturn(sell);

        SellDTO savedSell = repo.save(sell);

        assertEquals(sell, savedSell);
    }

}
