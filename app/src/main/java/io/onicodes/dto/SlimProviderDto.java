package io.onicodes.dto;

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
public class SlimProviderDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private boolean isActive;

    public static SlimProviderDto fromProvider(Provider provider) {
        return new SlimProviderDto(
            provider.getId(),
            provider.getFirstName(),
            provider.getLastName(),
            provider.getMiddleName(),
            provider.isActive()
        );
    }
}
