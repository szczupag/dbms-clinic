package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "medicalProcedures")
public class MedicalProcedure {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long medicalProcedureId;

  @NotNull
  private String name;

  private Long cost;

  @ManyToMany(mappedBy = "medicalProcedures", cascade = CascadeType.MERGE)
  private Set<Doctor> doctors;

  @ManyToOne(cascade = CascadeType.MERGE)
  private Treatment treatment;

  static MedicalProcedure from(CreationMedicalProcedure medicalProcedure, Set<Doctor> doctors) {
    return MedicalProcedure.builder()
            .name(medicalProcedure.getName())
            .cost(medicalProcedure.getCost())
            .doctors(doctors)
            .build();
  }
}
