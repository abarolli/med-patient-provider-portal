package io.onicodes.dto;

import java.time.OffsetDateTime;
import java.util.Optional;

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
public class AppointmentUpdateDto {
    
    private Optional<Long> id = Optional.empty();
    private Optional<PatientDto> patient = Optional.empty();
    private Optional<SlimLocationDto> location = Optional.empty();
    private Optional<SlimProviderDto> provider = Optional.empty();
    private Optional<OffsetDateTime> appointmenTime = Optional.empty();

}
