package com.nmatute.octoger.productmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

/**
 * Clase para Repositorio de Usuarios.
 * 
 * @author NM4TT
 */
@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository{
    
    private final IUserCrudRepository crud;
    private final UserMapper mapper;

    @Override
    public UserDTO getById(long id) {
        UserDTO user = mapper.toUserDTO(crud.findById(id).orElse(null));
        return user;
    }

}
