package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.TypeDTO;
z
/**
 * DAO de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
