package io.onicodes.dto;

import java.time.OffsetDateTime;

import io.onicodes.entity.Appointment;
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
public class AppointmentDto {
    
    private Long id;
    private PatientDto patient;
    private SlimLocationDto location;
    private SlimProviderDto provider;
    private OffsetDateTime appointmentTime;
    
    public static AppointmentDto fromAppointment(Appointment appointment) {
        return new AppointmentDto(
            appointment.getId(),
            PatientDto.fromPatient(appointment.getPatient()),
            SlimLocationDto.fromLocation(appointment.getLocation()),
            SlimProviderDto.fromProvider(appointment.getProvider()),
            appointment.getAppointmentTime()
        );
    }
}
