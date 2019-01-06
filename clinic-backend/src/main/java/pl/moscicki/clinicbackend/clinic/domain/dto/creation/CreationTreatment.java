package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationTreatment implements Serializable {
  @NotNull
  private Date startDate;
  private Date endDate;
  private Set<Long> medicalProceduresIds;
  private Long diseaseId;
  private String patientPesel;
}
