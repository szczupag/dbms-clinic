package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Disease;

import java.util.Date;
import java.util.Set;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiseaseResponse {

  private Date startDate;
  private Date endDate;
  private Set<String> medicalProceduresIds;
  private Long diseaseId;
  private String patientPesel;
}
