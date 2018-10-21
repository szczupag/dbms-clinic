package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Department;
import pl.moscicki.clinicbackend.clinic.domain.Doctor;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponse {

  @NotNull
  private String name;
  private Set<DoctorResponse> doctors;
  private Long clinicId;

  public static DepartmentResponse from(Department department) {
    return DepartmentResponse.builder()
            .name(department.getName())
            .doctors(mapDoctors(department.getDoctors()))
            .build();

  }

  private static Set<DoctorResponse> mapDoctors(Set<Doctor> doctors) {
    return doctors.stream().map(doctor -> DoctorResponse.builder()
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .speciality(doctor.getSpeciality())
            .build())
            .collect(Collectors.toSet());
  }
}
