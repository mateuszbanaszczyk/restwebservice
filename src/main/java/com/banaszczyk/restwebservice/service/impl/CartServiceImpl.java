package com.banaszczyk.restwebservice.service.impl;


import com.banaszczyk.restwebservice.exception.NoProductInStockException;
import com.banaszczyk.restwebservice.exception.NoProductsInCartException;
import com.banaszczyk.restwebservice.exception.ProductAlreadyInCartException;
import com.banaszczyk.restwebservice.model.Order;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.model.QuantityProduct;
import com.banaszczyk.restwebservice.service.CartService;
import com.banaszczyk.restwebservice.service.ProductService;
import com.banaszczyk.restwebservice.service.QuantityProductService;
import com.banaszczyk.restwebservice.utils.OrderHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final QuantityProductService quantityProductService;
    private final OrderHandler orderHandler;

    private List<Product> products = new ArrayList<>();

    @Autowired
    public CartServiceImpl(ProductService productService, QuantityProductService quantityProductService, OrderHandler orderHandler) {
        this.productService = productService;
        this.orderHandler = orderHandler;
        this.quantityProductService = quantityProductService;
    }

    @Override
    public void addProduct(Product product) throws ProductAlreadyInCartException {
        if (products.contains(product)) {
            throw new ProductAlreadyInCartException(product);
        } else {
            products.add(product);
        }
    }

    @Override
    public void removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
        }
    }


    @Override
    public List<Product> getProductsInCart() {
        return Collections.unmodifiableList(products);
    }


    @Override
    public Optional<List<NoProductInStockException>> checkout() throws NoProductsInCartException {
        if (products.isEmpty()) throw new NoProductsInCartException();
        List<Product> productsToRemoveFromCart = new ArrayList<>();
        List<NoProductInStockException> unavailableProducts = new ArrayList<>();
        List<QuantityProduct> checkoutList = new ArrayList<>();
        for (Product entry : products) {
            try {
                QuantityProduct quantityProduct = productService.getQuantity(entry);
                checkoutList.add(quantityProduct);
                quantityProduct.setAvailable(false);
            } catch (NoProductInStockException e) {
                productsToRemoveFromCart.add(entry);
                unavailableProducts.add(e);
            }
        }
        for (Product product : productsToRemoveFromCart) {
            products.remove(product);
        }
        if (!unavailableProducts.isEmpty()) {
            return Optional.of(unavailableProducts);
        }
        Order order = orderHandler.save(checkoutList);
        quantityProductService.saveAll(checkoutList);
        quantityProductService.flush();
        products.clear();
        return Optional.empty();
    }

    @Override
    public BigDecimal getTotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
