package pl.moscicki.clinicbackend.clinic.domain.dto.creation;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreationVisitor {
  @NotNull
  private String pesel;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  private String idNumber;
  private Set<Long> visitsIds;
}
