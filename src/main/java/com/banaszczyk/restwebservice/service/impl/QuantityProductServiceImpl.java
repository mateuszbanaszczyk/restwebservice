package com.banaszczyk.restwebservice.service.impl;

import com.banaszczyk.restwebservice.model.QuantityProduct;
import com.banaszczyk.restwebservice.repository.QuantityProductRepository;
import com.banaszczyk.restwebservice.service.QuantityProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuantityProductServiceImpl implements QuantityProductService {

    private final QuantityProductRepository quantityProductRepository;

    public QuantityProductServiceImpl(final QuantityProductRepository quantityProductRepository) {
        this.quantityProductRepository = quantityProductRepository;
    }

    @Override
    public QuantityProduct addQuantityProduct(QuantityProduct quantityProduct) {
        return quantityProductRepository.save(quantityProduct);
    }


    @Override
    public Optional<QuantityProduct> findById(Long quantityId) {
        return quantityProductRepository
                .findById(quantityId);
    }


    @Override
    public List<QuantityProduct> findAll() {
        return quantityProductRepository.findAll();
    }


    @Override
    public void delete(Long id) {
        quantityProductRepository.deleteById(id);
    }

    @Override
    public void save(QuantityProduct quantityProduct) {
        quantityProductRepository.save(quantityProduct);
    }

    @Override
    public void saveAll(List<QuantityProduct> products) {
        quantityProductRepository.saveAll(products);
    }

    @Override
    public void flush() {
        quantityProductRepository.flush();
    }


}
