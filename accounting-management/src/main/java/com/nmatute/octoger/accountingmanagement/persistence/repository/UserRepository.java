package com.nmatute.octoger.accountingmanagement.persistence.repository;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.accountingmanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;
import com.nmatute.octoger.accountingmanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.accountingmanagement.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository{
    
    private final IUserCrudRepository crud;
    private final UserMapper mapper;

    @Override
    public UserDTO getById(int id) {
        return mapper.toUserDTO(crud.findById(id).get());
    }

}
