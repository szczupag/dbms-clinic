package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.MedicalProcedure;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicalProcedureResponse {

  private Long id;
  private String name;
  private Long cost;
  private Set<String> doctors;

  public static MedicalProcedureResponse from(MedicalProcedure medicalProcedure, boolean withDoctors) {
    return MedicalProcedureResponse.builder()
            .id(medicalProcedure.getMedicalProcedureId())
            .name(medicalProcedure.getName())
            .cost(medicalProcedure.getCost())
            .doctors(withDoctors ? mapDoctors(medicalProcedure) : null)
            .build();
  }

  private static Set<String> mapDoctors(MedicalProcedure medicalProcedure) {
    return medicalProcedure.getDoctors().stream()
            .map(doctor -> doctor.getFirstName() + " " + doctor.getLastName())
            .collect(Collectors.toSet());
  }

}
