package com.nmatute.octoger.usermanagement.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.User;
import com.nmatute.octoger.usermanagement.persistence.mapper.TypeMapper;
import com.nmatute.octoger.usermanagement.persistence.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository{
    
    private final IUserCrudRepository crud;
    private final UserMapper mapper;
    private final TypeMapper typeMapper;

    @Override
    public List<UserDTO> getAll() {
        return mapper.toUserDTOs((List<User>) crud.findAll());
    }

    @Override
    public List<UserDTO> getByType(TypeDTO type) {
        return mapper.toUserDTOs(crud.findByType(typeMapper.toType(type)));
    }

    @Override
    public UserDTO getById(int id) {
        return crud.findById(id).map(user -> mapper.toUserDTO(user)).get();
    }

    @Override
    public UserDTO save(UserDTO user) {
        User usr = crud.save(mapper.toUser(user));
        return mapper.toUserDTO(usr);
    }

    @Override
    public void delete(int id) {
        crud.deleteById(id);
    }
    
    @Override
    public boolean findUser(int userId){
        return crud.findUser(userId) == 1;
    }

}
