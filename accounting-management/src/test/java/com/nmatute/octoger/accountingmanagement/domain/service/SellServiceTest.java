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

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.SellRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class SellServiceTest {
    
    @Mock
    private SellRepository repo;
    @InjectMocks
    private SellService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById(){
        SellDTO sell = new SellDTO();
        sell.setId(1);
        when(service.getById(1)).thenReturn(sell);

        SellDTO sellFound = service.getById(1);

        assertEquals(sell, sellFound);
    }

    @Test
    void getAll(){
        SellDTO sell = new SellDTO();
        when(service.getAll()).thenReturn(List.of(sell));

        List<SellDTO> sells = service.getAll();

        assertNotNull(sells);
    }

    @Test
    void getByCollection(){
        SellDTO sell = new SellDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        sell.setCollection(collection);
        when(service.getByCollection(collection)).thenReturn(List.of(sell));

        List<SellDTO> sells = service.getByCollection(collection);

        assertNotNull(sells);
    }

    @Test
    void getByResponsible(){
        SellDTO sell = new SellDTO();
        UserDTO user = new UserDTO();
        sell.setUser(user);
        when(service.getByResponsible(user)).thenReturn(List.of(sell));

        List<SellDTO> sells = service.getByResponsible(user);

        assertNotNull(sells);
    }

    @Test
    void getByProductOperation(){
        SellDTO sell = new SellDTO();
        ProductOperationDTO operation = new ProductOperationDTO();
        sell.setProductOperation(operation);
        when(service.getByProductOperation(operation)).thenReturn(sell);

        SellDTO sellFound = service.getByProductOperation(operation);

        assertNotNull(sellFound);
    }

    @Test
    void getByDateRange(){
        SellDTO sell = new SellDTO();
        Date from = new Date();
        Date to = new Date();
        when(service.getByDateRange(from, to)).thenReturn(List.of(sell));

        List<SellDTO> sells = service.getByDateRange(from, to);

        assertNotNull(sells);
    }

    @Test
    void save(){
        SellDTO sell = new SellDTO();
        when(service.save(sell)).thenReturn(sell);

        SellDTO savedSell = service.save(sell);

        assertEquals(sell, savedSell);
    }

}