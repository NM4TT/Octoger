package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;

/**
 * Interfaz DAO para Repositorio de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserRepository {
    
    List<UserDTO> getAll();

    List<UserDTO> getByType(TypeDTO type);

    UserDTO getById(int id);
    
    boolean findUser(int userId);
    
    UserDTO save(UserDTO user);
    
    void delete(int id);

}
