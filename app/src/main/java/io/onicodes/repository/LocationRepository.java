package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
