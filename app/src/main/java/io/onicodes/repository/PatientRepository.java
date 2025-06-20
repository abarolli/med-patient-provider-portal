package io.onicodes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.onicodes.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
