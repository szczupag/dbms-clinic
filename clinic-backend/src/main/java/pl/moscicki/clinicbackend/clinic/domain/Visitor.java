package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "visitors")
public class Visitor {
  @Id
  @NotNull
  @Column(length = 11)
  String pesel;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Size(max = 7)
  private String idNumber;

  @ManyToMany(mappedBy = "visitors")
  private Set<Patient> patients;
}
