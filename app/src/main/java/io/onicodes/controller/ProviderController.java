package io.onicodes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.onicodes.dto.ProviderDto;
import io.onicodes.service.ProviderService;

@Controller
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
        
        return ResponseEntity.ok(new PagedResponse<>(providerService
                .findProvidersByLocation(locationId)));
    }
}
