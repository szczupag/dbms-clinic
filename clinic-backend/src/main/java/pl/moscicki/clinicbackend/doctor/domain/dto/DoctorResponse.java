package pl.moscicki.clinicbackend.doctor.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.doctor.domain.Doctor;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorResponse {

  private String pesel;
  private String firstName;
  private String lastName;
  private Long salary;
  private String speciality;
  private String supervisor;

  public static DoctorResponse from(Doctor doctor) {
    return DoctorResponse.builder()
            .pesel(doctor.getPesel())
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .salary(doctor.getSalary())
            .speciality(doctor.getSpeciality())
            .supervisor(doctor.getSupervisor() != null ?
                    String.format("%s %s", doctor.getSupervisor().getFirstName(), doctor.getSupervisor().getLastName())
                            : null)
            .build();
  }

}
