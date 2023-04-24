package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.UserDTO;

/**
 * DAO de Usuarios.
 * 
 * @author NM4TT
 */
public interface IUserRepository {
    UserDTO getById(int id);
}
