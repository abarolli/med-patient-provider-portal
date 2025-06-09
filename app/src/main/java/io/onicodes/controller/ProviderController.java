package io.onicodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.onicodes.dto.ProviderDto;
import io.onicodes.service.ProviderService;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    
    @Autowired
    private ProviderService providerService;

    @GetMapping
    public ResponseEntity<PagedResponse<ProviderDto>> getProviders(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam Long locationId
    ) {
        
        page = Math.max(0, page - 1);
        return ResponseEntity.ok(new PagedResponse<>(providerService
                .findProvidersByLocation(locationId, page, size)));
    }
}
