package com.banaszczyk.restwebservice.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductAlreadyExistsException extends Exception {

    public ProductAlreadyExistsException(String name){
        super(name + " This product already exists in our store!");
    }


}
