package io.onicodes.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>  {
    
    public Page<Provider> findByLocations_Location_Id(Long locationId, Pageable pageable);
}
