package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationMedicalProcedure {

  @NotNull
  private String name;
  private Long cost;
  private Set<String> doctorsIds;
  private Date date;
}
