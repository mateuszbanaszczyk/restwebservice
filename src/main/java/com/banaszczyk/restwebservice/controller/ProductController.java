package com.banaszczyk.restwebservice.controller;


import com.banaszczyk.restwebservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banaszczyk.restwebservice.model.Product;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ProductController {


    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-list")
    public ResponseEntity<List<Product>> getAllProducts(){
        log.info("Return all product list");
        return ResponseEntity
                .ok()
                .body(productService.findAll());
    }


    @GetMapping("/product-list/{id}")
    public ResponseEntity<?> getproductById(@PathVariable("id") Long id) {
        log.info("return product by ID " + id);
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/product-list")
    public ResponseEntity<?> createProduct(@RequestBody final Product product) throws Exception {
        log.info("New product has been created");
        Product newProduct = productService.addProduct(product);
        return ResponseEntity
                .created(URI.create("/" + newProduct.getId()))
                .body(newProduct);
    }


    @DeleteMapping("/product-list/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {

        if (productService.findById(id).isPresent()) {
            productService.deleteById(id);
            log.info("product " + id + "has been deleted");
            return ResponseEntity
                    .ok()
                    .body(productService.findById(id));
        } else {
           return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/product-list/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody final Product product) {

       if( productService.findById(id).isPresent()) {
           product.setId(id);
           productService.save(product);
           log.info("product " + product + "has been updated");
           return ResponseEntity
                   .noContent()
                   .build();
       } else {
       return ResponseEntity.notFound().build();
       }

    }
}
