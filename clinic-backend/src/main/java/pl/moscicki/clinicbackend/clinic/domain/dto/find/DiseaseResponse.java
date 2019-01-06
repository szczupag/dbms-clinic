package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Disease;
import pl.moscicki.clinicbackend.clinic.domain.Medicine;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseResponse {

  private String name;
  private String severity;
  private Set<TreatmentResponse> treatments;
  private Set<MedicineResponse> medicines;

  public static DiseaseResponse from(Disease disease, boolean withMedicines) {
    return DiseaseResponse.builder()
            .name(disease.getName())
            .severity(disease.getSeverity())
            .medicines(withMedicines ? mapMedicines(disease) : null)
            .treatments(mapTreatments(disease))
            .build();
  }

  private static Set<TreatmentResponse> mapTreatments(Disease disease) {
    return null;
  }

  private static Set<MedicineResponse> mapMedicines(Disease disease) {
    return disease.getMedicines().stream()
            .map(medicine -> MedicineResponse.builder()
                    .name(medicine.getName())
                    .cost(medicine.getCost())
                    .recommendedDose(medicine.getRecommendedDose())
                    .build())
            .collect(Collectors.toSet());
  }
}
