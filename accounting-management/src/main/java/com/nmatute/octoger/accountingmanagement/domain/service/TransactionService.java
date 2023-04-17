package com.nmatute.octoger.accountingmanagement.domain.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.accountingmanagement.domain.dto.TransactionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repo;
    
    
    public TransactionDTO getById(int id) {
        return repo.getById(id);
    }

    public List<TransactionDTO> getAll(){
        return repo.getAll();
    }

    public List<TransactionDTO> getByType(TypeDTO type) {
        return repo.getByType(type);
    }

    
    public List<TransactionDTO> getByDateRange(Date from, Date to) {
        return repo.getByDateRange(from, to);
    }

    
    public TransactionDTO save(TransactionDTO transaction) {
        return repo.save(transaction);
    }

    
    public void delete(int id) {
        repo.delete(id);
    }
    
}
