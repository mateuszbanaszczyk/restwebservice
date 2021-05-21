package com.banaszczyk.restwebservice.repository;


import com.banaszczyk.restwebservice.model.QuantityProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuantityProductRepository extends JpaRepository<QuantityProduct, Long> {

   Optional<QuantityProduct> findById(Long id);



}
