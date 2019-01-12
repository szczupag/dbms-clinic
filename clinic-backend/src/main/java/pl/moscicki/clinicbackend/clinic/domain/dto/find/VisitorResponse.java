package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Visitor;

import java.util.Set;
import java.util.stream.Collectors;


@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VisitorResponse {

  private String pesel;
  private String firstName;
  private String lastName;
  private String idNumber;
  private Set<VisitResponse> visits;

  public static VisitorResponse from(Visitor visitor, boolean withVisits) {
    return VisitorResponse.builder()
            .pesel(visitor.getPesel())
            .firstName(visitor.getFirstName())
            .lastName(visitor.getLastName())
            .idNumber(visitor.getIdNumber())
            .visits(withVisits ? mapFromVisits(visitor) : null)
            .build();
  }

  private static Set<VisitResponse> mapFromVisits(Visitor visitor) {
    return visitor.getVisits().stream()
            .map(visit -> VisitResponse.from(visit, false, true))
            .collect(Collectors.toSet());
  }
}
