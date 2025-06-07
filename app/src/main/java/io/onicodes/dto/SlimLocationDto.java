package io.onicodes.dto;

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
public class SlimLocationDto {
    private Long id;
    private String address;
    private String stripeAccount;
    private boolean isActive;
    
    public static SlimLocationDto fromLocation(Location location) {
        return new SlimLocationDto(
            location.getId(),
            location.getAddress(),
            location.getStripeAccount(),
            location.isActive()
        );
    }
}
