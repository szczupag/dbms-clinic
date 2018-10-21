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

  @ManyToMany(mappedBy = "medicalProcedures")
  private Set<Doctor> doctors;

  @ManyToMany(mappedBy = "medicalProcedures")
  private Set<Treatment> treatments;

  static MedicalProcedure from(CreationMedicalProcedure medicalProcedure, Set<Doctor> doctors) {
    return MedicalProcedure.builder()
            .name(medicalProcedure.getName())
            .cost(medicalProcedure.getCost())
            .doctors(doctors)
            .build();
  }
}
