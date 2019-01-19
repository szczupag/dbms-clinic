package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Treatment;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponse {

  private Long id;
  private Date startDate;
  private Date endDate;
  private Set<MedicalProcedureResponse> medicalProcedures;
  private DiseaseResponse disease;
  private PatientResponse patient;

  public static TreatmentResponse from(Treatment treatment, boolean withMedicalProcedures, boolean withDisease, boolean withPatient) {
    return TreatmentResponse.builder()
            .id(treatment.getTreatmentId())
            .startDate(treatment.getStartDate())
            .endDate(treatment.getEndDate())
            .medicalProcedures(withMedicalProcedures ? mapMedicalProcedures(treatment) : null)
            .disease(withDisease ? mapDisease(treatment) : null)
            .patient(withPatient ? mapPatient(treatment) : null)
            .build();
  }

  private static Set<MedicalProcedureResponse> mapMedicalProcedures(Treatment treatment) {
    return treatment.getMedicalProcedures().stream()
            .map(medicalProcedure -> MedicalProcedureResponse.from(medicalProcedure, true, false))
            .collect(Collectors.toSet());
  }

  private static DiseaseResponse mapDisease(Treatment treatment) {
    return Objects.nonNull(treatment.getDisease()) ? DiseaseResponse.from(treatment.getDisease(),false) : null;
  }

  private static PatientResponse mapPatient(Treatment treatment) {
    return Objects.nonNull(treatment.getPatient()) ? PatientResponse.from(treatment.getPatient(),
            false, false) : null;
  }
}
