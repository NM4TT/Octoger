package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductOperationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductOperationService {
    
    private final ProductOperationRepository repo;

    public ProductOperationDTO getById(int id) {
        return repo.getById(id).orElse(null);
    }

    
    public List<ProductOperationDTO> getByCollection(ProductCollectionDTO collection) {
        return repo.getByCollection(collection).orElse(null);
    }

    
    public List<ProductOperationDTO> getByType(TypeDTO type) {
        return repo.getByType(type).orElse(null);
    }

    
    public List<ProductOperationDTO> getByResponsible(UserDTO user) {
        return repo.getByResponsible(user).orElse(null);
    }

    
    public List<ProductOperationDTO> getByTransaction(TransactionDTO transaction) {
        return repo.getByTransaction(transaction).orElse(null);
    }

    
    public List<ProductOperationDTO> getByDateRange(Date from, Date to) {
        return repo.getByDateRange(from, to).orElse(null);
    }

    
    public ProductOperationDTO save(ProductOperationDTO productOperation) {
        return repo.save(productOperation);
    }

    
    public void delete(int id) {
        repo.delete(id);
    }

}
