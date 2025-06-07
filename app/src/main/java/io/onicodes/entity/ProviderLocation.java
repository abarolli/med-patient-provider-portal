package io.onicodes.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
@Table(name = "provider_locations")
public class ProviderLocation {

    @EmbeddedId
    private ProviderLocationId id = new ProviderLocationId();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne // many ProviderLocations can be associated with a location
    @MapsId("locationId")
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne // many ProviderLocations can be associated with a provider
    @MapsId("providerId")
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

}
