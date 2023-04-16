package com.nmatute.octoger.accountingmanagement.persistence.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository{
    
    private IUserCrudRepository crud;
    private UserMapper mapper;

    @Override
    public Optional<UserDTO> getById(int id) {
        return crud.findById(id).map(user -> mapper.toUserDTO(user));
    }

}
