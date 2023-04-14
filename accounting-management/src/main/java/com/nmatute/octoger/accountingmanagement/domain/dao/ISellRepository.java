package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

public interface ISellRepository {
    
    Optional<SellDTO> getById(int id);

    Optional<List<SellDTO>> getByCollection(ProductCollectionDTO collection);

    Optional<List<SellDTO>> getByResponsible(UserDTO user);

    Optional<List<SellDTO>> getByProductOperation(ProductOperationDTO productOperation);

    Optional<List<SellDTO>> getByDateRange(Date from, Date to);

    SellDTO save(SellDTO sell);

    void delete(int id);

}
