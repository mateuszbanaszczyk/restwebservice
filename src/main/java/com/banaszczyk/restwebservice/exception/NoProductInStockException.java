package com.banaszczyk.restwebservice.exception;

import com.banaszczyk.restwebservice.model.Product;

public class NoProductInStockException extends Exception {

    public static final String DEFAULT_MESSAGE = "No product in stock";

    public NoProductInStockException() {
        super(DEFAULT_MESSAGE);
    }

    public NoProductInStockException(Product product){
        super(String.format("No %s product in stock.", product.getName()));
    }
}
