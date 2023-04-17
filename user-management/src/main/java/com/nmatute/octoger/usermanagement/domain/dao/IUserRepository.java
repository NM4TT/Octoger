package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;

import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;

public interface IUserRepository {
    
    List<UserDTO> getAll();

    List<UserDTO> getByType(String type);

    UserDTO getById(int id);
    
    boolean findUser(int userId);
    
    UserDTO save(UserDTO user);
    
    void delete(int id);

}
