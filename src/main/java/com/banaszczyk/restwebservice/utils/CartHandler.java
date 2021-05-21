package com.banaszczyk.restwebservice.utils;

import com.banaszczyk.restwebservice.exception.NoProductInStockException;
import com.banaszczyk.restwebservice.exception.NoProductsInCartException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.service.ProductService;
import com.banaszczyk.restwebservice.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartHandler {

    private final ProductService productService;
    private final CartServiceImpl cartService;

    private List<String> messages = new ArrayList<>();

    public CartHandler(ProductService productService, CartServiceImpl cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public Product findProductById(Long id) {
        if (productService.findById(id).isPresent()) {
            return productService.findById(id).get();
        } else {
            return null;
        }
    }

    public ResponseEntity<?> checkoutErrorHandler() {
        Optional<List<NoProductInStockException>> errors;
        List<String> errorMessages = new ArrayList<>();
        try {
            errors = cartService.checkout();
            if(errors.isPresent()){
                errorMessages = errors
                        .get()
                        .stream()
                        .map(Throwable::getMessage)
                        .collect(Collectors.toList());
                }
        } catch (NoProductsInCartException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        if (!errorMessages.isEmpty()){
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessages);
        }
        return ResponseEntity
                .ok(cartService.getProductsInCart());
    }
}

