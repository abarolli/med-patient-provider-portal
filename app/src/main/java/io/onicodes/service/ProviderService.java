package io.onicodes.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.onicodes.dto.ProviderCreationDto;
import io.onicodes.dto.ProviderDto;
import io.onicodes.entity.Location;
import io.onicodes.entity.Provider;
import io.onicodes.repository.LocationRepository;
import io.onicodes.repository.ProviderRepository;
import jakarta.transaction.Transactional;

@Service
public class ProviderService {
    
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Transactional
    public ProviderDto create(ProviderCreationDto creationRequest) {
        var provider = new Provider();
        provider.setFirstName(creationRequest.getFirstName());
        provider.setLastName(creationRequest.getLastName());
        provider.setMiddleName(creationRequest.getMiddleName());
        
        Set<Long> locationIds = creationRequest
            .getLocations()
            .stream()
            .map(location -> location.getId())
            .collect(Collectors.toSet());
        
        List<Location> locations = locationRepository.findAllById(locationIds);
        
        locations.forEach(provider::addLocation);

        return ProviderDto.fromProvider(providerRepository.save(provider));
    }
}
