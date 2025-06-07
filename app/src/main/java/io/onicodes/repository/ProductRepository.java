package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
