package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.SellRepository;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Servicio de Ventas.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class SellService {

    private final SellRepository repo;
    
    public SellDTO getById(long id) {
        return repo.getById(id);
    }
    
    public List<SellDTO> getAll(){
        return repo.getAll();
    }
    
    public List<SellDTO> getByCollection(ProductCollectionDTO collection) {
        return repo.getByCollection(collection);
    }

    
    public List<SellDTO> getByResponsible(UserDTO user) {
        return repo.getByResponsible(user);
    }

    
    public SellDTO getByProductOperation(ProductOperationDTO productOperation) {
        return repo.getByProductOperation(productOperation);
    }

    
    public List<SellDTO> getByDateRange(Date from, Date to) {
        return repo.getByDateRange(from, to);
    }
    
    public SellDTO save(SellDTO sell) {
        return repo.save(sell);
    }

    
    public void delete(long id) {
        repo.delete(id);
    }
    
}
