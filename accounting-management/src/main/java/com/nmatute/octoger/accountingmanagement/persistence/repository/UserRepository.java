package com.nmatute.octoger.accountingmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;

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
    public UserDTO getById(int id) {
        UserDTO user = mapper.toUserDTO(crud.findById(id).orElse(null));
        return user;
    }

}
