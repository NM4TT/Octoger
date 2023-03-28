package com.nmatute.octoger.usermanagement.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nmatute.octoger.usermanagement.domain.dao.IUserRepository;
import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.crud.IUserCrudRepository;
import com.nmatute.octoger.usermanagement.persistence.entity.User;
import com.nmatute.octoger.usermanagement.persistence.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository{
    
    private IUserCrudRepository crud;
    private UserMapper mapper;

    @Override
    public List<UserDTO> getAll() {
        return mapper.toUserDTOs((List<User>) crud.findAll());
    }

    @Override
    public List<UserDTO> getByType(String type) {
        return mapper.toUserDTOs(crud.findByType(type));
    }

    @Override
    public Optional<UserDTO> getById(int id) {
        return crud.findById(id).map(user -> mapper.toUserDTO(user));
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

    public List<UserDTO> findByType(String type){
        return mapper.toUserDTOs(crud.findByType(type));
    }
    
    public boolean findUser(int userId){
        return crud.findUser(userId) == 1;
    }

}
