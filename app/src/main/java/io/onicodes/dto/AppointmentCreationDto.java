package io.onicodes.dto;


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
    
    private Long patientId;
    private Long locationId;
    private Long providerId;
    private String localDateTime;
    private String timeZone;

}
