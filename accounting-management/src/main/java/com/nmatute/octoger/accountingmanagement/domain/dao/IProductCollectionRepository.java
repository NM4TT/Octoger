package com.nmatute.octoger.accountingmanagement.domain.dao;

import com.nmatute.octoger.accountingmanagement.domain.dto.ProductCollectionDTO;

public interface IProductCollectionRepository {
    ProductCollectionDTO getById(int id);
}
