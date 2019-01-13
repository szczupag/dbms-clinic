package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Department;
import pl.moscicki.clinicbackend.clinic.domain.Doctor;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponse {

  private Long id;
  private String name;
  private Set<DoctorResponse> doctors;
  private ClinicResponse clinic;

  public static DepartmentResponse from(Department department, boolean withClinic, boolean withDoctors) {
    return DepartmentResponse.builder()
            .id(department.getDepartmentId())
            .name(department.getName())
            .clinic(withClinic ? mapClinic(department) : null)
            .doctors(withDoctors ? mapDoctors(department.getDoctors()) : null)
            .build();

  }

  private static Set<DoctorResponse> mapDoctors(Set<Doctor> doctors) {
    return doctors.stream()
            .map(doctor -> DoctorResponse.from(doctor, false))
            .collect(Collectors.toSet());

  }

  private static ClinicResponse mapClinic(Department department) {
    return Objects.nonNull(department.getClinic()) ? ClinicResponse.from(department.getClinic(), false, false) :
            null;
  }
}
