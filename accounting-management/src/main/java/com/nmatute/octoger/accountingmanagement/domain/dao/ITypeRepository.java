package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

/**
 * DAO de Tipos.
 * 
 * @author NM4TT
 */
public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
