package com.banaszczyk.restwebservice.exception;


public class ProductDoesNotExistsException extends Exception{

    public ProductDoesNotExistsException(){
        super( "This product not exists in our stock!");
    }

}
