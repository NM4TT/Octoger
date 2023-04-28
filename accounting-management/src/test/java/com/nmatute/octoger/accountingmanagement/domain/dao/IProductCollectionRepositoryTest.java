package com.nmatute.octoger.accountingmanagement.domain.dao;

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
import com.nmatute.octoger.accountingmanagement.persistence.crud.IProductCollectionCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductCollectionRepository;

/**
 * Clase para testear Repositorio de Coleccion de Productos.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class IProductCollectionRepositoryTest {
    
    @Mock
    private IProductCollectionCrudRepository crud;
    @Mock
    private ProductCollectionMapper mapper;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private ProductCollectionRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getById(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        collection.setId(1);
        when(repo.getById(1)).thenReturn(collection);

        ProductCollectionDTO collectionFound = repo.getById(1);
        
        assertEquals(collection, collectionFound);
    }
}
