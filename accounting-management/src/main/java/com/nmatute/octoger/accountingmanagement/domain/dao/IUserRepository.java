package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

public interface IUserRepository {
    public UserDTO getById(int id);
}
