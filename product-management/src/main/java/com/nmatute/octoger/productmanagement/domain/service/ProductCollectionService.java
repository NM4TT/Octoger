package com.nmatute.octoger.productmanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.ProductCollectionRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Coleccion de Productos.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class ProductCollectionService {
    
    private final ProductCollectionRepository repo;

    public ProductCollectionDTO getById(long id){
        return repo.getById(id);
    }
    public List<ProductCollectionDTO> getByResponsible(UserDTO user){
        return repo.getByResponsible(user);
    }
    public List<ProductCollectionDTO> getByProvider(String provider){
        return repo.getByProvider(provider);
    }
    public ProductCollectionDTO save(ProductCollectionDTO productCollection){
        return repo.save(productCollection);
    }
    public void delete(long id){
        repo.delete(id);
    }
    public List<ProductCollectionDTO> getAll() {
        return repo.getAll();
    }

}
