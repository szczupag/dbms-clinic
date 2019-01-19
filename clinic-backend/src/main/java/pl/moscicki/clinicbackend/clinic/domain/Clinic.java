package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationClinic;

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

  @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Department> departments;

  @OneToOne(cascade = CascadeType.ALL)
  private Localization localization;

  static Clinic from(CreationClinic creationClinic, Localization localization, Set<Department> departments) {
    return Clinic.builder()
            .name(creationClinic.getName())
            .type(creationClinic.getType())
            .localization(localization)
            .departments(departments)
            .build();
  }
}
