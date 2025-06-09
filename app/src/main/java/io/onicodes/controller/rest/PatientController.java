package io.onicodes.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.onicodes.dto.PatientDto;
import io.onicodes.service.PatientService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patients")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @GetMapping("/json")
    public ResponseEntity<PagedResponse<PatientDto>> getPatients(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size) {

        page = Math.max(0, page - 1); // maintains 1-based pagination
        var patientsPage = patientService.getPatients(page, size);
        return ResponseEntity.ok(new PagedResponse<>(patientsPage));
    }
}
