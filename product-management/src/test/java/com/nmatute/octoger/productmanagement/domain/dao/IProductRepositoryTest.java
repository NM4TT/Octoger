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
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IProductCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductCollectionMapper;
import com.nmatute.octoger.productmanagement.persistence.mapper.ProductMapper;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductRepository;

/**
 * Clase para testear Repositorio de Productos en web service.
 * 
 * @author NM4TT
 */
@TestInstance(Lifecycle.PER_CLASS)
public class IProductRepositoryTest {

    @Mock
    private IProductCrudRepository crud;
    @Mock
    private ProductMapper mapper;
    @Mock
    private ProductCollectionMapper collectionMapper;
    @InjectMocks
    private ProductRepository repo;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        when(repo.getById(1)).thenReturn(product);

        ProductDTO productFound = repo.getById(1);

        assertEquals(product, productFound);
    }

    @Test
    void getByCollection() {
        ProductDTO product = new ProductDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        product.setProductCollection(collection);
        when(repo.getByCollection(collection)).thenReturn(List.of(product));

        List<ProductDTO> products = repo.getByCollection(collection);

        assertNotNull(products);
    }

    @Test
    void getAvailables() {
        ProductDTO product = new ProductDTO();
        product.setAvailable(true);
        when(repo.getAvailables()).thenReturn(List.of(product));

        List<ProductDTO> products = repo.getAvailables();

        assertNotNull(products);
    }

    @Test
    void getNonAvailables() {
        ProductDTO product = new ProductDTO();
        product.setAvailable(false);
        when(repo.getAvailables()).thenReturn(List.of(product));

        List<ProductDTO> products = repo.getNonAvailables();

        assertNotNull(products);
    }

    @Test
    void save() {
        ProductDTO product = new ProductDTO();
        when(repo.save(product)).thenReturn(product);

        ProductDTO savedProduct = repo.save(product);

        assertEquals(product, savedProduct);
    }

    @Test
    void getAll() {
        ProductDTO product = new ProductDTO();
        when(repo.getAll()).thenReturn(List.of(product));

        List<ProductDTO> products = repo.getAll();

        assertNotNull(products);
    }
    
}
