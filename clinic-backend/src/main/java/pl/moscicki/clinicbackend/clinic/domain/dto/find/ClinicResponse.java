package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Clinic;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClinicResponse {

  private Long id;
  private String name;
  private String type;
  private Set<DepartmentResponse> departments;
  private LocalizationResponse localization;

  public static ClinicResponse from(Clinic clinic, boolean withLocalization, boolean withDepartments) {
    return ClinicResponse.builder()
            .id(clinic.getClinicId())
            .name(clinic.getName())
            .type(clinic.getType())
            .departments(withDepartments ? mapDepartments(clinic) : null)
            .localization(withLocalization ? mapLocalization(clinic) : null)
            .build();
  }

  private static Set<DepartmentResponse> mapDepartments(Clinic clinic) {
    return clinic.getDepartments().stream()
            .map(department -> DepartmentResponse.from(department, false, false))
            .collect(Collectors.toSet());
  }

  private static LocalizationResponse mapLocalization(Clinic clinic) {
    return Objects.nonNull(clinic.getLocalization()) ?
            LocalizationResponse.from(clinic.getLocalization(), false)
            : null;
  }
}
