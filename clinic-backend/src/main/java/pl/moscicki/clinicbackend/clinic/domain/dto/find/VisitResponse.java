package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Visit;

import java.util.Date;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitResponse {

  private Date visitDate;
  private PatientResponse patient;
  private VisitorResponse visitor;

  public static VisitResponse from(Visit visit, boolean withVisitor, boolean withPatient) {
    return VisitResponse.builder()
            .visitDate(visit.getVisitDate())
            .patient(withPatient ? mapFromPatient(visit) : null)
            .visitor(withVisitor ? mapFromVisitor(visit) : null)
            .build();
  }

  private static PatientResponse mapFromPatient(Visit visit) {
    return PatientResponse.from(visit.getPatient(), false);
  }

  private static VisitorResponse mapFromVisitor(Visit visit) {
    return VisitorResponse.from(visit.getVisitor(), false);
  }
}
