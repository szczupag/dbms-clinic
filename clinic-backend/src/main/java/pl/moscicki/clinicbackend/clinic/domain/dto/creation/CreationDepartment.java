package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationDepartment implements Serializable {

  @NotNull
  private String name;
  private Set<String> doctorsPesels;
  private Long clinicId;
}
