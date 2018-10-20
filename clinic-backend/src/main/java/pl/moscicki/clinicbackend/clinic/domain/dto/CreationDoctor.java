package pl.moscicki.clinicbackend.clinic.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreationDoctor implements Serializable {

  @NotNull
  @Size(min = 11, max = 11)
  private String pesel;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  private Long salary;
  @NotNull
  private String speciality;
  private String supervisorId;
}
