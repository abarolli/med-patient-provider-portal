package io.onicodes.service;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Could not fund product with id " + id);
    }
}
