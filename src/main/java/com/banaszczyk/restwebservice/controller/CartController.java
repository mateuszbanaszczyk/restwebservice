package com.banaszczyk.restwebservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.banaszczyk.restwebservice.utils.CartHandler;
import com.banaszczyk.restwebservice.exception.ProductAlreadyInCartException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.service.CartService;
import com.banaszczyk.restwebservice.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CartHandler cartHandler;

    public CartController(CartService cartService, ProductService productService, CartHandler cartHandler) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartHandler = cartHandler;
    }

    @GetMapping("/cart")
    ResponseEntity<List<Product>> cartContent(){
        log.info("reading cart contents");
        return ResponseEntity.ok(cartService.getProductsInCart());
    }

    @GetMapping("/cart/recalculate")
    ResponseEntity<BigDecimal> recalculateOrder(){
        log.info("Recalculate the order");
        return ResponseEntity.ok(cartService.getTotal());
    }

    @GetMapping("/cart/addProduct/{id}")
    ResponseEntity<List<Product>> addProductToCart(@PathVariable("id") Long id) throws ProductAlreadyInCartException {
        log.info("adding product to cart");
        Product product = cartHandler.findProductById(id);
        if (product == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        cartService.addProduct(product);
        return cartContent();
    }

    @GetMapping("/cart/removeProduct/{id}")
    ResponseEntity<List<Product>> removeProductFromCart(@PathVariable("id") Long id){
        log.info("removing product from cart");
        Product product = cartHandler.findProductById(id);
        if (product != null) cartService.removeProduct(product);
        return cartContent();
    }

    @GetMapping("/cart/checkout")
    ResponseEntity<?> checkout() {
        ResponseEntity<?> response = cartHandler.checkoutErrorHandler();
        return response;
    }
}
