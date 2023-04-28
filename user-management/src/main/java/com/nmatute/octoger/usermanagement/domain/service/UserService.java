package com.nmatute.octoger.usermanagement.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.UserRepository;

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

    
    public List<UserDTO> getAll() {
        return repo.getAll();
    }

    
    public List<UserDTO> getByType(TypeDTO type) {
        return repo.getByType(type);
    }

    
    public UserDTO getById(long id) {
        return repo.getById(id);
    }

    
    public UserDTO save(UserDTO user) {
        return repo.save(user);
    }

    
    public void delete(long id) {
        repo.delete(id);
    }

    public boolean findUser(long userId){
        return repo.findUser(userId);
    }

    public UserDTO findById(long id){
        return repo.getById(id);
    }

}
