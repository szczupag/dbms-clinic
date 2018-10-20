package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationTreatment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "treatments")
public class Treatment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long treatmentId;

  @NotNull
  private String name;

  private Long cost;

  @ManyToMany(mappedBy = "treatments")
  private Set<Doctor> doctors;

  static Treatment from(CreationTreatment treatment, Set<Doctor> doctors) {
    return Treatment.builder()
            .name(treatment.getName())
            .cost(treatment.getCost())
            .doctors(doctors)
            .build();
  }
}
