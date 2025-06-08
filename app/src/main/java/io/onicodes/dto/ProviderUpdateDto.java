package io.onicodes.dto;

import java.util.Optional;
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
public class ProviderUpdateDto {
    private Optional<Long> id = Optional.empty();
    private Optional<String> firstName = Optional.empty();
    private Optional<String> lastName = Optional.empty();
    private Optional<String> middleName = Optional.empty();
    private Optional<Boolean> isActive = Optional.empty();
    private Optional<Set<SlimLocationDto>> locations = Optional.empty();
}
