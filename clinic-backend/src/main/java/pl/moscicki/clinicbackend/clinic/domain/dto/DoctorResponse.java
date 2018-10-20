package pl.moscicki.clinicbackend.clinic.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Doctor;
import pl.moscicki.clinicbackend.clinic.domain.Treatment;

import java.util.Set;
import java.util.stream.Collectors;

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
  private Set<String> treatments;

  public static DoctorResponse from(Doctor doctor, boolean withTreatments) {
    return DoctorResponse.builder()
            .pesel(doctor.getPesel())
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .salary(doctor.getSalary())
            .speciality(doctor.getSpeciality())
            .supervisor(mapSupervisor(doctor))
            .treatments(mapTreatments(doctor, withTreatments))
            .build();
  }

  private static String mapSupervisor(Doctor doctor) {
    return doctor.getSupervisor() != null ?
            String.format("%s %s", doctor.getSupervisor().getFirstName(), doctor.getSupervisor().getLastName())
            : null;
    }

  private static Set<String> mapTreatments(Doctor doctor, boolean withTreatments) {
    return withTreatments ? doctor.getTreatments().stream()
            .map(Treatment::getName)
            .collect(Collectors.toSet()) : null;
  }
  }


