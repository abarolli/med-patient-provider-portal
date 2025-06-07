package io.onicodes.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor 
@Getter
@Setter
@Embeddable
public class ProviderLocationId implements Serializable {
    private Long providerId;
    private Long locationId;
}
