package com.nmatute.octoger.usermanagement.domain.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.nmatute.octoger.usermanagement.persistence.entity.User;

@Component
public interface IUserRepository {
    
    List<User> getAll();

    Optional<List<User>> getByType();

}
