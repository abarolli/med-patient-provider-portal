package io.onicodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.onicodes.dto.LocationDto;
import io.onicodes.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<PagedResponse<LocationDto>> getLocations(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        
        page = Math.max(0, page - 1);
        var locationsPage = locationService.getLocations(page, size);
        return ResponseEntity.ok(new PagedResponse<>(locationsPage));
    }
}
