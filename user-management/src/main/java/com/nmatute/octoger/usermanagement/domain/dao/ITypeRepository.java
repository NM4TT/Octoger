package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;

/**
 * Interfaz DAO para Repositorio de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
