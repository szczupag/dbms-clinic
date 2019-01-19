package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Patient;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResponse {
  private String pesel;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private Set<TreatmentResponse> treatments;
  private Set<VisitResponse> visits;


  public static PatientResponse from(Patient patient, boolean withTreatments, boolean withVisits) {
    return PatientResponse.builder()
            .pesel(patient.getPesel())
            .firstName(patient.getFirstName())
            .lastName(patient.getLastName())
            .phoneNumber(patient.getPhoneNumber())
            .treatments(withTreatments ? mapTreatments(patient) : null)
            .visits(withVisits ? mapVisits(patient) : null)
            .build();
  }

  private static Set<VisitResponse> mapVisits(Patient patient) {
    return patient.getVisits().stream()
            .map(visit -> VisitResponse.from(visit, true, false))
            .collect(Collectors.toSet());
  }

  private static Set<TreatmentResponse> mapTreatments(Patient patient) {
    return patient.getTreatments().stream()
            .map(treatment -> TreatmentResponse.from(treatment, true, true, false))
            .collect(Collectors.toSet());
  }

}
