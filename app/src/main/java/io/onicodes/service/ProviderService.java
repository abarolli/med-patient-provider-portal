package io.onicodes.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.onicodes.dto.LocationDto;
import io.onicodes.dto.ProviderCreationDto;
import io.onicodes.dto.ProviderDto;
import io.onicodes.dto.ProviderUpdateDto;
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
    public ProviderDto findById(Long id) {
        var provider = providerRepository
            .findById(id)
            .orElseThrow(() -> new RecordNotFoundException(Provider.class, id));

        return ProviderDto.fromProvider(provider);
    }

    @Transactional
    public Page<ProviderDto> findProvidersByLocation(LocationDto location, int page, int size) {
        return findProvidersByLocation(location.getId(), page, size);
    }
    
    @Transactional
    public Page<ProviderDto> findProvidersByLocation(Long locationId, int page, int size) {
        Page<Provider> providers = providerRepository
            .findByLocations_Location_Id(
                locationId,
                PageRequest.of(page, size));
    
        return providers.map(ProviderDto::fromProvider);
    }

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

    @Transactional
    public ProviderDto update(ProviderUpdateDto updateRequest) {
        Long providerId = updateRequest.getId().get();
        var provider = providerRepository
            .findById(providerId)
            .orElseThrow(() -> new RecordNotFoundException(Provider.class, providerId));
        
        updateRequest.getFirstName().ifPresent(provider::setFirstName);
        updateRequest.getLastName().ifPresent(provider::setLastName);
        updateRequest.getMiddleName().ifPresent(provider::setMiddleName);
        updateRequest.getIsActive().ifPresent(provider::setActive);

        var optionalLocations = updateRequest.getLocations();
        if (optionalLocations.isPresent()) {
            var locationIds = optionalLocations
                .get()
                .stream()
                .map(location -> location.getId())
                .collect(Collectors.toSet());
            
            var locations = new HashSet<Location>(locationRepository.findAllById(locationIds));
            provider.updateLocations(locations);
        }

        return ProviderDto.fromProvider(provider);
    }
}
