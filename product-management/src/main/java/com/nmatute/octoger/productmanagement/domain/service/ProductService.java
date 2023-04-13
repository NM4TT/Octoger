package com.nmatute.octoger.productmanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.ProductDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repo;

    public ProductDTO getById(int id){
        return repo.getById(id);
    }
    public List<ProductDTO> getByCollection(ProductCollectionDTO collection){
        return repo.getByCollection(collection).orElse(null);
    }
    public List<ProductDTO> getAvailables(){
        return repo.getAvailables().orElse(null);
    }
    public List<ProductDTO> getNonAvailables(){
        return repo.getNonAvailables().orElse(null);
    }
    public ProductDTO save(ProductDTO product){
        return repo.save(product);
    }
    public void delete(int id){
        repo.delete(id);
    }
    public List<ProductDTO> getAll() {
        return repo.getAll().orElse(null);
    }

}
