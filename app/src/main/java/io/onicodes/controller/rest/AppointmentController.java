package io.onicodes.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.onicodes.dto.AppointmentCreationDto;
import io.onicodes.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/schedule/json")
    public ResponseEntity<String> scheduleAppointment(@ModelAttribute AppointmentCreationDto appointment) {
        
        var success = appointmentService.create(appointment);
        return ResponseEntity.ok("Appointment scheduled for " + success.getAppointmentTime().toString());
    }
}
