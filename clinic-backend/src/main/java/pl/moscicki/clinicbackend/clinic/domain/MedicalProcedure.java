package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

  @Temporal(TemporalType.DATE)
  private Date date;

  @ManyToMany(mappedBy = "medicalProcedures")
  private Set<Doctor> doctors;

  @ManyToOne
  private Treatment treatment;

  static MedicalProcedure from(CreationMedicalProcedure medicalProcedure, Set<Doctor> doctors) {
    return MedicalProcedure.builder()
            .name(medicalProcedure.getName())
            .cost(medicalProcedure.getCost())
            .doctors(doctors)
            .date(medicalProcedure.getDate())
            .build();
  }
}
