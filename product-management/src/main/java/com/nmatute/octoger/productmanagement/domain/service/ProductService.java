package com.nmatute.octoger.productmanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Productos.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repo;

    public ProductDTO getById(int id){
        return repo.getById(id);
    }
    public List<ProductDTO> getByCollection(ProductCollectionDTO collection){
        return repo.getByCollection(collection);
    }
    public List<ProductDTO> getAvailables(){
        return repo.getAvailables();
    }
    public List<ProductDTO> getNonAvailables(){
        return repo.getNonAvailables();
    }
    public ProductDTO save(ProductDTO product){
        return repo.save(product);
    }
    public void delete(int id){
        repo.delete(id);
    }
    public List<ProductDTO> getAll() {
        return repo.getAll();
    }

}
