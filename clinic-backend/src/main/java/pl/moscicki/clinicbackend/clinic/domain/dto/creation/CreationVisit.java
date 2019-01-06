package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.moscicki.clinicbackend.clinic.domain.Visitor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreationVisit implements Serializable {

  @NotNull
  private Date visitDate;
  private String patientPesel;
  private String visitorPesel;
}
