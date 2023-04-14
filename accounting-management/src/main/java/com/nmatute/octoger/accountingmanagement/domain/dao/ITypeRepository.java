package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Optional;

import com.nmatute.octoger.accountingmanagement.domain.dto.TypeDTO;

public interface ITypeRepository {

    Optional<TypeDTO> getByIdentifier(String identifier);

}
