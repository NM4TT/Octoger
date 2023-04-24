package com.nmatute.octoger.accountingmanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductCollectionRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ProductCollectionServiceTest {
    
    @Mock
    private ProductCollectionRepository repo;
    @InjectMocks
    private ProductCollectionService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getById(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        collection.setId(1);
        when(service.getById(1)).thenReturn(collection);

        ProductCollectionDTO collectionFound = service.getById(1);
        
        assertEquals(collection, collectionFound);
    }
}
