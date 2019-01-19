package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.MedicalProcedure;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalProcedureResponse {

  private Long id;
  private String name;
  private Long cost;
  private Set<DoctorResponse> doctors;
  private Date date;
  private TreatmentResponse treatment;

  public static MedicalProcedureResponse from(MedicalProcedure medicalProcedure, boolean withDoctors, boolean withTreatments) {
    return MedicalProcedureResponse.builder()
            .id(medicalProcedure.getMedicalProcedureId())
            .name(medicalProcedure.getName())
            .cost(medicalProcedure.getCost())
            .date(medicalProcedure.getDate())
            .doctors(withDoctors ? mapDoctors(medicalProcedure) : null)
            .treatment(withTreatments ? mapTreatment(medicalProcedure) : null)
            .build();
  }

  private static TreatmentResponse mapTreatment(MedicalProcedure medicalProcedure) {
    return Objects.nonNull(medicalProcedure.getTreatment()) ?
            TreatmentResponse.from(medicalProcedure.getTreatment(), false, false, false) :
            null;
  }

  private static Set<DoctorResponse> mapDoctors(MedicalProcedure medicalProcedure) {
    return medicalProcedure.getDoctors().stream()
            .map(doctor -> DoctorResponse.from(doctor, false, false, false))
            .collect(Collectors.toSet());
  }

}
