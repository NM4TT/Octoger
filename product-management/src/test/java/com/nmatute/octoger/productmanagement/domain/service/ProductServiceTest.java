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
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductRepository;

@TestInstance(Lifecycle.PER_CLASS)
public class ProductServiceTest {
    
    @Mock
    private ProductRepository repo;
    @InjectMocks
    private ProductService service;

    @BeforeAll
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {
        ProductDTO product = new ProductDTO();
        product.setId(1);
        when(service.getById(1)).thenReturn(product);

        ProductDTO productFound = service.getById(1);

        assertEquals(product, productFound);
    }

    @Test
    void getByCollection() {
        ProductDTO product = new ProductDTO();
        ProductCollectionDTO collection = new ProductCollectionDTO();
        product.setProductCollection(collection);
        when(service.getByCollection(collection)).thenReturn(List.of(product));

        List<ProductDTO> products = service.getByCollection(collection);

        assertNotNull(products);
    }

    @Test
    void getAvailables() {
        ProductDTO product = new ProductDTO();
        product.setAvailable(true);
        when(service.getAvailables()).thenReturn(List.of(product));

        List<ProductDTO> products = service.getAvailables();

        assertNotNull(products);
    }

    @Test
    void getNonAvailables() {
        ProductDTO product = new ProductDTO();
        product.setAvailable(false);
        when(service.getAvailables()).thenReturn(List.of(product));

        List<ProductDTO> products = service.getNonAvailables();

        assertNotNull(products);
    }

    @Test
    void save() {
        ProductDTO product = new ProductDTO();
        when(service.save(product)).thenReturn(product);

        ProductDTO savedProduct = service.save(product);

        assertEquals(product, savedProduct);
    }

    @Test
    void getAll() {
        ProductDTO product = new ProductDTO();
        when(service.getAll()).thenReturn(List.of(product));

        List<ProductDTO> products = service.getAll();

        assertNotNull(products);
    }

}
