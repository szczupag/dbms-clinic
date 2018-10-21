package pl.moscicki.clinicbackend.clinic.domain.dto.creation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreationLocalization implements Serializable {

  @NotNull
  private String city;
  @NotNull
  private String street;
  @NotNull
  private String postalCode;
  private Long buildingNo;
  private Long clinicId;
}
