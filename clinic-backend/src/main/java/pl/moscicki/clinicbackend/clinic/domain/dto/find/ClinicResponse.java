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
  private String localization;

  public static ClinicResponse from(Clinic clinic) {
    return ClinicResponse.builder()
            .id(clinic.getClinicId())
            .name(clinic.getName())
            .type(clinic.getType())
            .departments(mapDepartments(clinic))
            .localization(mapLocalization(clinic))
            .build();
  }

  private static Set<DepartmentResponse> mapDepartments(Clinic clinic) {
    return clinic.getDepartments().stream()
            .map(DepartmentResponse::from)
            .collect(Collectors.toSet());
  }

  private static String mapLocalization(Clinic clinic) {
    return Objects.nonNull(clinic.getLocalization()) ?
            String.format("%s %s %s", clinic.getLocalization().getCity(), clinic.getLocalization().getStreet(),
                    clinic.getLocalization().getBuildingNo())
            : null;
  }
}
