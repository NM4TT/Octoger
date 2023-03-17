package com.nmatute.octoger.usermanagement.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.repository.UserRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
    
    private UserRepository repo;

    
    public List<UserDTO> getAll() {
        return repo.getAll();
    }

    
    public List<UserDTO> getByType(String type) {
        return repo.getByType(type);
    }

    
    public Optional<UserDTO> getById(int id) {
        return repo.getById(id);
    }

    
    public UserDTO save(UserDTO user) {
        return repo.save(user);
    }

    
    public void delete(int id) {
        repo.delete(id);
    }

}
