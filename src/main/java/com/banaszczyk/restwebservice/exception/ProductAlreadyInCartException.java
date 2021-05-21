package com.banaszczyk.restwebservice.exception;

import com.banaszczyk.restwebservice.model.Product;

public class ProductAlreadyInCartException extends Throwable {

    public static final String DEFAULT_MESSAGE = "This product is already in cart";

    public  ProductAlreadyInCartException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductAlreadyInCartException(Product product) {
        super(String.format("%s is already in cart",product.getName()));
    }
}
