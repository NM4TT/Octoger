package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Optional;

import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

public interface IUserRepository {
    public Optional<UserDTO> getById(int id);
}
