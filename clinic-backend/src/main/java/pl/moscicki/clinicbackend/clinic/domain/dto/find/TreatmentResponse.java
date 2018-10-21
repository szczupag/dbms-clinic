package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.MedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.Treatment;

import java.util.Date;
import java.util.Set;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponse {
  private Date startDate;
  private Date endDate;
  private Set<String> medicalProcedures;
  private Long diseaseId;
  private String patientPesel;

//  public static TreatmentResponse from(Treatment treatment) {
//    return TreatmentResponse.builder()
//            .startDate(treatment.getStartDate())
//            .endDate(treatment.getEndDate())
//            .medicalProceduresIds(mapDoctors(medicalProcedure, withDoctors))
//            .build();
//  }
}
