package com.banaszczyk.restwebservice.service.impl;

import com.banaszczyk.restwebservice.exception.NoProductInStockException;
import com.banaszczyk.restwebservice.exception.ProductAlreadyExistsException;
import com.banaszczyk.restwebservice.model.Product;
import com.banaszczyk.restwebservice.model.QuantityProduct;
import com.banaszczyk.restwebservice.repository.ProductRepository;
import com.banaszczyk.restwebservice.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product addProduct(Product product) throws ProductAlreadyExistsException {


        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new ProductAlreadyExistsException(product.getName());
        }
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);

    }
    @Override
    public QuantityProduct getQuantity(Product product) throws NoProductInStockException {
        List<QuantityProduct> quantity = product.getQuantity();
        Optional<QuantityProduct> availableQuantity = quantity.stream()
                .filter(QuantityProduct::isAvailable)
                .findFirst();

        if (availableQuantity.isPresent()){
            return availableQuantity.get();
        }

        throw new NoProductInStockException(product);
    }
}



