package io.onicodes.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "providers")
public class Provider {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String middleName;

    @Column(nullable = false)
    private boolean isActive = true;

    // providers can be associated with many locations
    @OneToMany(mappedBy = "provider", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<ProviderLocation> locations = new HashSet<>();

    public void addLocation(Location location) {
        var providerLocation = new ProviderLocation();
        providerLocation.setProvider(this);
        providerLocation.setLocation(location);
        locations.add(providerLocation);
    }

    public void updateLocations(Set<Location> updatedLocations) {
        Set<Long> newIds = updatedLocations
            .stream()
            .map(location -> location.getId())
            .collect(Collectors.toSet());

        locations.removeIf(pl -> !newIds.contains(pl.getLocation().getId()));

        Set<Long> existingIds = locations
            .stream()
            .map(pl -> pl.getLocation().getId())
            .collect(Collectors.toSet());

        for (var location : updatedLocations) {
            if (!existingIds.contains(location.getId())) {
                var newProviderLocation = new ProviderLocation();
                newProviderLocation.setLocation(location);
                newProviderLocation.setProvider(this);
                locations.add(newProviderLocation);
            }
        }
    }
}
