package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>  {
    
}
