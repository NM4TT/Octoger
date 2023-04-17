package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;

public interface IUserRepository {
    UserDTO getById(int id);
}
