package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Doctor;

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
  private Set<MedicalProcedureResponse> medicalProcedures;
  private DepartmentResponse department;
  private DoctorResponse supervisor;

  public static DoctorResponse from(Doctor doctor, boolean withMedicalProcedures, boolean withDepartment, boolean withSupervisor) {
    return DoctorResponse.builder()
            .pesel(doctor.getPesel())
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .salary(doctor.getSalary())
            .speciality(doctor.getSpeciality())
            .supervisor(withSupervisor ? mapSupervisor(doctor) : null)
            .department(withDepartment ? mapDepartment(doctor) : null)
            .medicalProcedures(withMedicalProcedures ? mapMedicalProcedures(doctor) : null)
            .build();
  }

  private static DepartmentResponse mapDepartment(Doctor doctor) {
    return doctor.getDepartment() != null ?
            DepartmentResponse.from(doctor.getDepartment(), true, false) :
            null;
  }

  private static DoctorResponse mapSupervisor(Doctor doctor) {
    return doctor.getSupervisor() != null ?
            DoctorResponse.from(doctor.getSupervisor(), false, false, false)
            : null;
    }

  private static Set<MedicalProcedureResponse> mapMedicalProcedures(Doctor doctor) {
    return doctor.getMedicalProcedures().stream()
            .map(medicalProcedure -> MedicalProcedureResponse.from(medicalProcedure, false, false))
            .collect(Collectors.toSet());
  }
}


