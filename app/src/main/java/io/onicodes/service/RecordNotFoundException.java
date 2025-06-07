package io.onicodes.service;


public class RecordNotFoundException extends RuntimeException {
    public <T> RecordNotFoundException(Class<T> entity, Long id) {
        super("Could not find record for " + entity.getSimpleName() + " with id " + id);
    }
}
