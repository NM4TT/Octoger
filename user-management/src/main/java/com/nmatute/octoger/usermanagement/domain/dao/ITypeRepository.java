package com.nmatute.octoger.usermanagement.domain.dao;

import com.nmatute.octoger.usermanagement.domain.dto.TypeDTO;

public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
