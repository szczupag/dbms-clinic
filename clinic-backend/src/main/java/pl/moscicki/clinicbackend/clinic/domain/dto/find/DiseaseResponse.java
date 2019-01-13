package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Disease;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseResponse {

  private Long id;
  private String name;
  private String severity;
  private Set<TreatmentResponse> treatments;

  public static DiseaseResponse from(Disease disease, boolean withTreatments) {
    return DiseaseResponse.builder()
            .id(disease.getDiseaseId())
            .name(disease.getName())
            .severity(disease.getSeverity())
            .treatments(withTreatments ? mapTreatments(disease) : null)
            .build();
  }

  private static Set<TreatmentResponse> mapTreatments(Disease disease) {
    return disease.getTreatments().stream()
            .map(treatment -> TreatmentResponse.from(treatment, false, false, false))
            .collect(Collectors.toSet());
  }
}
