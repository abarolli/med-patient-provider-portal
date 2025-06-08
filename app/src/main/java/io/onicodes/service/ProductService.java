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
    public ProductDto findById(Long id) {
        Product product = productRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException(Product.class, id));

        return ProductDto.fromProduct(product);
    }

    @Transactional
    public ProductDto create(ProductCreationDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        return ProductDto.fromProduct(productRepository.save(newProduct));
    }

    @Transactional
    public ProductDto update(ProductDto updateRequest) {
        Product product = productRepository
            .findById(updateRequest.getId())
            .orElseThrow(() -> new RecordNotFoundException(Product.class, updateRequest.getId()));

        product.setName(updateRequest.getName());
        return ProductDto.fromProduct(product);
    }
}
