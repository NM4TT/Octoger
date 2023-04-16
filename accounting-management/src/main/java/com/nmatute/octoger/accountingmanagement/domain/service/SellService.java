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

@Service
@RequiredArgsConstructor
public class SellService {

    private final SellRepository repo;
    
    public SellDTO getById(int id) {
        return repo.getById(id).orElse(null);
    }
    
    public List<SellDTO> getAll(){
        return repo.getAll().orElse(null);
    }
    
    public List<SellDTO> getByCollection(ProductCollectionDTO collection) {
        return repo.getByCollection(collection).orElse(null);
    }

    
    public List<SellDTO> getByResponsible(UserDTO user) {
        return repo.getByResponsible(user).orElse(null);
    }

    
    public SellDTO getByProductOperation(ProductOperationDTO productOperation) {
        return repo.getByProductOperation(productOperation).orElse(null);
    }

    
    public List<SellDTO> getByDateRange(Date from, Date to) {
        return repo.getByDateRange(from, to).orElse(null);
    }
    
    public SellDTO save(SellDTO sell) {
        return repo.save(sell);
    }

    
    public void delete(int id) {
        repo.delete(id);
    }
    
}
