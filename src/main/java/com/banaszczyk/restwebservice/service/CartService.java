package com.banaszczyk.restwebservice.service;

import com.banaszczyk.restwebservice.exception.NoProductInStockException;
import com.banaszczyk.restwebservice.exception.NoProductsInCartException;
import com.banaszczyk.restwebservice.exception.ProductAlreadyInCartException;
import com.banaszczyk.restwebservice.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartService {

    void addProduct(Product product) throws ProductAlreadyInCartException;

    void removeProduct(Product product);

    List<Product> getProductsInCart();

    Optional<List<NoProductInStockException>> checkout() throws NoProductInStockException, NoProductsInCartException;

    BigDecimal getTotal();
}