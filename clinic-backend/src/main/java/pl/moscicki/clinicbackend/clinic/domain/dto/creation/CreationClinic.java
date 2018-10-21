package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreationClinic implements Serializable {

  @NotNull
  private String name;
  @NotNull
  private String type;
  private Long departmentId;
  private Long localizationId;
}
