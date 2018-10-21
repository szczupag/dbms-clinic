package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "clinics")
public class Clinic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long clinicId;

  @NotNull
  private String name;

  @NotNull
  private String type;

  @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY)
  private Set<Department> departments;

  @OneToOne
  private Localization localization;


}
