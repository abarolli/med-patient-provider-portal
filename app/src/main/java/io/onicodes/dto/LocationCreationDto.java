package io.onicodes.dto;

import java.util.HashSet;
import java.util.Set;

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
public class LocationCreationDto {
    
    private String address;
    private String stripeAccount;
    private Set<ProviderDto> providers = new HashSet<>();

    public LocationCreationDto(String address, String stripeAccount) {
        this.address = address;
        this.stripeAccount = stripeAccount;
    }
}
