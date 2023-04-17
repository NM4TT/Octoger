package com.nmatute.octoger.productmanagement.domain.dao;

import com.nmatute.octoger.productmanagement.domain.dto.TypeDTO;

public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
