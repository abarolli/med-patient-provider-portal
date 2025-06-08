package io.onicodes.dto;

import java.time.OffsetDateTime;

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
public class AppointmentCreationDto {
    
    private PatientDto patient;
    private SlimLocationDto location;
    private SlimProviderDto provider;
    private OffsetDateTime appointmentTime;

}
