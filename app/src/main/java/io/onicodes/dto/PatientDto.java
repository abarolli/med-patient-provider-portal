package io.onicodes.dto;

import io.onicodes.entity.Patient;
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
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private boolean isActive;

    public static PatientDto fromPatient(Patient patient) {
        return new PatientDto(
            patient.getId(),
            patient.getFirstName(),
            patient.getLastName(),
            patient.getMiddleName(),
            patient.getAge(),
            patient.isActive()
        );
    }
}
