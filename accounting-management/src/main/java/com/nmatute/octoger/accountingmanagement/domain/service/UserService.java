package com.nmatute.octoger.accountingmanagement.domain.service;

import org.springframework.stereotype.Service;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repo;

    public UserDTO getById(int id) {
        return repo.getById(id);
    }

}
