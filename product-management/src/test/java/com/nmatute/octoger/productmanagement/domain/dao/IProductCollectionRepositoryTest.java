package com.nmatute.octoger.productmanagement.domain.dao;

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
import com.nmatute.octoger.productmanagement.persistence.crud.IProductCollectionCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.productmanagement.persistence.mapper.UserMapper;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductCollectionRepository;

/**
 * Clase para testear Repositorio de Coleccion de Productos en web service.
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

    @Test
    void getByResponsible(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        UserDTO user = new UserDTO();
        collection.setUser(user);
        when(repo.getByResponsible(user)).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = repo.getByResponsible(user);

        assertNotNull(collections);
    }

    @Test
    void getByProvider(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        collection.setProvider("provider");
        when(repo.getByProvider("provider")).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = repo.getByProvider("provider");

        assertNotNull(collections);
    }

    @Test
    void getAll(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        when(repo.getAll()).thenReturn(List.of(collection));

        List<ProductCollectionDTO> collections = repo.getAll();

        assertNotNull(collections);
    }

    @Test
    void save(){
        ProductCollectionDTO collection = new ProductCollectionDTO();
        when(repo.save(collection)).thenReturn(collection);

        ProductCollectionDTO savedCollection = repo.save(collection);

        assertEquals(collection, savedCollection);
    }

}
