package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationPatient implements Serializable {
  @NotNull
  @Size(min = 11, max = 11)
  private String pesel;

  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  private String phoneNumber;
  private Set<Long> treatmentIds;
  private Set<Long> visitIds;

}
