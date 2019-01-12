package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationDisease {
  @NotNull
  private String name;
  private String severity;
  private Set<Long> treatmentsIds;
}
