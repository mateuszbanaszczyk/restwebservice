package com.banaszczyk.restwebservice.service;

import com.banaszczyk.restwebservice.model.QuantityProduct;

import java.util.List;
import java.util.Optional;

public interface QuantityProductService {


    QuantityProduct addQuantityProduct(QuantityProduct quantityProduct);

    Optional<QuantityProduct> findById(Long quantityId);

    List<QuantityProduct> findAll ();

    void delete(Long quantityId);

    void save(QuantityProduct quantityProduct);

    void saveAll(List<QuantityProduct> products);

    void flush();
}
