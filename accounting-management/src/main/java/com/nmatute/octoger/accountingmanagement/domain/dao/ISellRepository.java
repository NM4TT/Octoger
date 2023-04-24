package com.nmatute.octoger.accountingmanagement.domain.dao;

import java.util.Date;
import java.util.List;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.ProductOperationDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.SellDTO;
import com.nmatute.octoger.accountingmanagement.domain.dto.UserDTO;

/**
 * DAO de Ventas.
 * 
 * @author NM4TT
 */
public interface ISellRepository {
    
    SellDTO getById(int id);

    List<SellDTO> getAll();

    List<SellDTO> getByCollection(ProductCollectionDTO collection);

    List<SellDTO> getByResponsible(UserDTO user);

    SellDTO getByProductOperation(ProductOperationDTO productOperation);

    List<SellDTO> getByDateRange(Date from, Date to);

    SellDTO save(SellDTO sell);

    void delete(int id);

}
