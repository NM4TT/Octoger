package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

/**
 * DAO de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserRepository {
    public UserDTO getById(int id);
}
