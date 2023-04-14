package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.ProductCollectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCollectionService {
    
    private final ProductCollectionRepository repo;

    public ProductCollectionDTO getById(int id){
        return repo.getById(id);
    }
}
