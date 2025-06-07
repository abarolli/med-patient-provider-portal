package io.onicodes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.onicodes.dto.PatientCreationDto;
import io.onicodes.dto.PatientDto;
import io.onicodes.dto.PatientUpdateDto;
import io.onicodes.entity.Patient;
import io.onicodes.repository.PatientRepository;
import jakarta.transaction.Transactional;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public PatientDto findById(Long id) {
        var patient = patientRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException(Patient.class, id));

        return PatientDto.fromPatient(patient);
    }
    
    @Transactional
    public PatientDto create(PatientCreationDto creationRequest) {

        Patient patient = new Patient();
        patient.setFirstName(creationRequest.getFirstName());
        patient.setLastName(creationRequest.getLastName());
        patient.setMiddleName(creationRequest.getMiddleName());
        patient.setAge(creationRequest.getAge());
        return PatientDto.fromPatient(patientRepository.save(patient));
    }

    @Transactional
    public PatientDto update(PatientUpdateDto updateRequest) {
        long patientId = updateRequest.getId().get();
        var patient = patientRepository
            .findById(patientId)
            .orElseThrow(() ->
                new RecordNotFoundException(Patient.class, patientId));
        
        updateRequest.getFirstName().ifPresent(patient::setFirstName);
        updateRequest.getLastName().ifPresent(patient::setLastName);
        updateRequest.getMiddleName().ifPresent(patient::setMiddleName);
        updateRequest.getAge().ifPresent(patient::setAge);
        updateRequest.getIsActive().ifPresent(patient::setActive);

        return PatientDto.fromPatient(patient);
    }
}
