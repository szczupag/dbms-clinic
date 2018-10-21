package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Doctor;
import pl.moscicki.clinicbackend.clinic.domain.MedicalProcedure;

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
  private Set<String> medicalProcedures;

  public static DoctorResponse from(Doctor doctor, boolean withMedicalProcedures) {
    return DoctorResponse.builder()
            .pesel(doctor.getPesel())
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .salary(doctor.getSalary())
            .speciality(doctor.getSpeciality())
            .supervisor(mapSupervisor(doctor))
            .medicalProcedures(mapMedicalProcedures(doctor, withMedicalProcedures))
            .build();
  }

  private static String mapSupervisor(Doctor doctor) {
    return doctor.getSupervisor() != null ?
            String.format("%s %s", doctor.getSupervisor().getFirstName(), doctor.getSupervisor().getLastName())
            : null;
    }

  private static Set<String> mapMedicalProcedures(Doctor doctor, boolean withMedicalProcedures) {
    return withMedicalProcedures ? doctor.getMedicalProcedures().stream()
            .map(MedicalProcedure::getName)
            .collect(Collectors.toSet()) : null;
  }
  }


