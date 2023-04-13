package com.nmatute.octoger.productmanagement.domain.dao;

import java.util.Optional;

import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;

public interface IUserRepository {
    Optional<UserDTO> getById(int id);
}
