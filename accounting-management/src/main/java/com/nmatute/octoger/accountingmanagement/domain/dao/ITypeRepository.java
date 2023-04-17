package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

public interface ITypeRepository {

    TypeDTO getByIdentifier(String identifier);

}
