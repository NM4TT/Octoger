package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;

public interface IUserRepository {
    
    List<UserDTO> getAll();

    List<UserDTO> getByType(String type);

    Optional<UserDTO> getById(int id);
    
    UserDTO save(UserDTO user);
    
    void delete(int id);

}
