package pl.moscicki.clinicbackend.clinic.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Treatment;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponse {

  private String name;
  private Long cost;
  private Set<String> doctors;

  public static TreatmentResponse from(Treatment treatment, boolean withDoctors) {
    return TreatmentResponse.builder()
            .name(treatment.getName())
            .cost(treatment.getCost())
            .doctors(mapDoctors(treatment, withDoctors))
            .build();
  }

  private static Set<String> mapDoctors(Treatment treatment, boolean withDoctors) {
    return withDoctors ? treatment.getDoctors().stream()
            .map(doctor -> doctor.getFirstName() + " " + doctor.getLastName())
            .collect(Collectors.toSet()) : null;
  }

}
