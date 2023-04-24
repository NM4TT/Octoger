package com.nmatute.octoger.productmanagement.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductCollectionRepository;

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

    @Test
    void getByResponsible(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        UserDTO user = new UserDTO();
        collection.setUser(user);
        when(service.getByResponsible(user)).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = service.getByResponsible(user);

        assertNotNull(collections);
    }

    @Test
    void getByProvider(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        collection.setProvider("provider");
        when(service.getByProvider("provider")).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = service.getByProvider("provider");

        assertNotNull(collections);
    }

    @Test
    void getAll(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        when(service.getAll()).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = service.getAll();

        assertNotNull(collections);
    }

    @Test
    void save(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        when(service.save(collection)).thenReturn(collection);

        ProductCollectionDTO savedCollection = service.save(collection);

        assertEquals(collection, savedCollection);
    }
}
