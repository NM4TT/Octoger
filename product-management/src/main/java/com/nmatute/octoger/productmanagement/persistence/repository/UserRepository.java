package com.nmatute.octoger.productmanagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.productmanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.productmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.productmanagement.persistence.entity.User;
import com.nmatute.octoger.productmanagement.persistence.mapper.UserMapper;

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