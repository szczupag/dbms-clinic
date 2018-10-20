package pl.moscicki.clinicbackend.clinic.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationTreatment {

  @NotNull
  private String name;
  private Long cost;
  private Set<String> doctorsIds;
}
