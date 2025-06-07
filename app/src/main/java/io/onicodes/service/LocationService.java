package io.onicodes.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.onicodes.dto.LocationCreationDto;
import io.onicodes.dto.LocationDto;
import io.onicodes.entity.Location;
import io.onicodes.repository.LocationRepository;
import io.onicodes.repository.ProviderRepository;
import jakarta.transaction.Transactional;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Transactional
    public LocationDto create(LocationCreationDto creationRequest) {
        Location location = new Location();
        location.setAddress(creationRequest.getAddress());
        location.setStripeAccount(creationRequest.getStripeAccount());
        
        Set<Long> providerIds = creationRequest
            .getProviders()
            .stream()
            .map(provider -> provider.getId())
            .collect(Collectors.toSet());
        
        var providers = providerRepository.findAllById(providerIds);
        
        providers.forEach(location::addProvider);

        return LocationDto.fromLocation(locationRepository.save(location));
    }
}
