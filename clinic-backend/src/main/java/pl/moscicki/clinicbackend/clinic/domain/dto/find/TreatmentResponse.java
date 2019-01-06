package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.MedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.Treatment;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponse {
  private Date startDate;
  private Date endDate;
  private Set<String> medicalProcedures;
  private DiseaseResponse disease;
  private PatientResponse patient;

  public static TreatmentResponse from(Treatment treatment, boolean withMedicalProcedures, boolean withDisease, boolean withPatient) {
    return TreatmentResponse.builder()
            .startDate(treatment.getStartDate())
            .endDate(treatment.getEndDate())
            .medicalProcedures(withMedicalProcedures ? mapMedicalProcedures(treatment) : null)
            .disease(withDisease ? mapDisease(treatment) : null)
            .patient(withPatient ? mapPatient(treatment) : null)
            .build();
  }

  private static Set<String> mapMedicalProcedures(Treatment treatment) {
    return treatment.getMedicalProcedures().stream()
            .map(MedicalProcedure::getName)
            .collect(Collectors.toSet());
  }

  private static DiseaseResponse mapDisease(Treatment treatment) {
    return DiseaseResponse.from(treatment.getDisease(),false);
  }

  private static PatientResponse mapPatient(Treatment treatment) {
    return PatientResponse.from(treatment.getPatient(), false);
  }
}
