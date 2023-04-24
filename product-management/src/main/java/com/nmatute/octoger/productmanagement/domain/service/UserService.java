package com.nmatute.octoger.productmanagement.domain.service;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio de Usuarios.
 * 
 * @author NM4TT
 */
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repo;

    public UserDTO getById(int id) {
        return repo.getById(id);
    }

}
