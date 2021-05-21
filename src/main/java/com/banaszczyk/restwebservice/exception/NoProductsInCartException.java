package com.banaszczyk.restwebservice.exception;

public class NoProductsInCartException extends Exception {

    public static final String DEFAULT_MESSAGE = "No products in cart, can't checkout";

    public NoProductsInCartException() {
        super(DEFAULT_MESSAGE);
    }
}
