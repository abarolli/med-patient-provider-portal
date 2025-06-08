package io.onicodes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.onicodes.dto.AppointmentCreationDto;
import io.onicodes.dto.AppointmentDto;
import io.onicodes.dto.AppointmentUpdateDto;
import io.onicodes.entity.Appointment;
import io.onicodes.entity.Location;
import io.onicodes.entity.Patient;
import io.onicodes.entity.Provider;
import io.onicodes.repository.AppointmentRepository;
import io.onicodes.repository.LocationRepository;
import io.onicodes.repository.PatientRepository;
import io.onicodes.repository.ProviderRepository;
import jakarta.transaction.Transactional;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Transactional
    public AppointmentDto create(AppointmentCreationDto creationRequest) {
        
        var appointment = new Appointment();
        
        var patientId = creationRequest.getPatient().getId();
        var patient = patientRepository
            .findById(patientId)
            .orElseThrow(() -> new RecordNotFoundException(Patient.class, patientId));

        var locationId = creationRequest.getLocation().getId();
        var location = locationRepository
            .findById(locationId)
            .orElseThrow(() -> new RecordNotFoundException(Location.class, locationId));

        var providerId = creationRequest.getProvider().getId();
        var provider = providerRepository
            .findById(providerId)
            .orElseThrow(() -> new RecordNotFoundException(Provider.class, providerId));

        appointment.setPatient(patient);
        appointment.setLocation(location);
        appointment.setProvider(provider);
        appointment.setAppointmentTime(creationRequest.getAppointmentTime());
        return AppointmentDto.fromAppointment(appointmentRepository.save(appointment));
    }

    @Transactional
    public AppointmentDto update(AppointmentUpdateDto updateRequest) {
        
        Long appointmentId = updateRequest.getId().get();
        var appointment = appointmentRepository
            .findById(appointmentId)
            .orElseThrow(() -> new RecordNotFoundException(Appointment.class, appointmentId));

        var optionalPatient = updateRequest.getPatient();
        if (optionalPatient.isPresent()) {
            var patientId = optionalPatient.get().getId();
            var patient = patientRepository
                .findById(patientId)
                .orElseThrow(() -> new RecordNotFoundException(Patient.class, patientId));

            appointment.setPatient(patient);
        }

        var optionalLocation = updateRequest.getLocation();
        if (optionalLocation.isPresent()) {
            var locationId = optionalLocation.get().getId();
            var location = locationRepository
                .findById(locationId)
                .orElseThrow(() -> new RecordNotFoundException(Location.class, locationId));
            
            appointment.setLocation(location);
        }

        var optionalProvider = updateRequest.getProvider();
        if (optionalProvider.isPresent()) {
            var providerId = optionalProvider.get().getId();
            var provider = providerRepository
                .findById(providerId)
                .orElseThrow(() -> new RecordNotFoundException(Provider.class, providerId));

            appointment.setProvider(provider);
        }

        updateRequest.getAppointmenTime().ifPresent(appointment::setAppointmentTime);
        
        return AppointmentDto.fromAppointment(appointment);
    }
}
