package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.ProviderLocation;
import io.onicodes.entity.ProviderLocationId;

@Repository
public interface ProviderLocationRepository extends JpaRepository<ProviderLocation, ProviderLocationId> {
    
}
