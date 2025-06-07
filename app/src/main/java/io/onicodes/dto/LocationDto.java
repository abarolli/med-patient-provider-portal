package io.onicodes.dto;

import java.util.Set;
import java.util.stream.Collectors;

import io.onicodes.entity.Location;
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
public class LocationDto {
    private Long id;
    private String address;
    private String stripeAccount;
    private boolean isActive;
    private Set<SlimProviderDto> providers;

    public static LocationDto fromLocation(Location location) {
        return new LocationDto(
            location.getId(),
            location.getAddress(),
            location.getStripeAccount(),
            location.isActive(),
            location.getProviders()
                .stream()
                .map(providerLocation -> SlimProviderDto.fromProvider(providerLocation.getProvider()))
                .collect(Collectors.toSet())
        );
    }
}
