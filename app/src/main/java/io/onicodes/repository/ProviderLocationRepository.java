package io.onicodes.repository;

import java.security.Provider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderLocationRepository extends JpaRepository<Provider, Long> {
    
}
