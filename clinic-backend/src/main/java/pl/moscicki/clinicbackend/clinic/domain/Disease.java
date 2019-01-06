package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDisease;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "diseases")
public class Disease {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long diseaseId;

  @NotNull
  private String name;

  private String severity;

  @OneToMany(mappedBy = "disease", fetch = FetchType.LAZY)
  private Set<Treatment> treatments;

  static Disease from(CreationDisease creationDisease, Set<Treatment> treatments) {
    return Disease.builder()
            .name(creationDisease.getName())
            .severity(creationDisease.getSeverity())
            .treatments(treatments)
            .build();
  }

}
