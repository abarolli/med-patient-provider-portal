package io.onicodes.dto;

import java.util.Set;
import java.util.stream.Collectors;

import io.onicodes.entity.Provider;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ProviderDto {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean isActive;
    private Set<SlimLocationDto> locations;
    
    public static ProviderDto fromProvider(Provider provider) {
        return new ProviderDto(
            provider.getId(),
            provider.getFirstName(),
            provider.getLastName(),
            provider.getMiddleName(),
            provider.isActive(),
            provider.getLocations()
                .stream()
                .map(providerLocation -> 
                    SlimLocationDto
                        .fromLocation(providerLocation.getLocation()))
                .collect(Collectors.toSet())
        );
    }
}
