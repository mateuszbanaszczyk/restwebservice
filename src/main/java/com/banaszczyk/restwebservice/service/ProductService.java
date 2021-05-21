package com.banaszczyk.restwebservice.service;

import com.banaszczyk.restwebservice.exception.NoProductInStockException;
import com.banaszczyk.restwebservice.exception.ProductAlreadyExistsException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.model.QuantityProduct;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    Product addProduct(Product product) throws ProductAlreadyExistsException;
  
    Optional<Product> findById(Long id);

    List<Product> findAll();

    Optional<Product> findByName(String name);

    void deleteById(Long id);

    void save (Product product);

    QuantityProduct getQuantity(Product product) throws NoProductInStockException;







}
