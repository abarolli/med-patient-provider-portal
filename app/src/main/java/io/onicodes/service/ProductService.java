package io.onicodes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.onicodes.dto.ProductCreationDto;
import io.onicodes.dto.ProductDto;
import io.onicodes.entity.Product;
import io.onicodes.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void create(ProductCreationDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        productRepository.save(newProduct);
    }

    @Transactional
    public void update(ProductDto updateRequest) {
        Product product = productRepository
            .findById(updateRequest.getId())
            .orElseThrow(() -> new RecordNotFoundException(Product.class, updateRequest.getId()));

        product.setName(updateRequest.getName());
    }

    @Transactional
    public ProductDto findById(Long id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException(Product.class, id));

        return new ProductDto(id, product.getName());
    }

    @Transactional
    public Long delete(Long id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException(Product.class, id));

        productRepository.delete(product);
        return id;
    }
}
