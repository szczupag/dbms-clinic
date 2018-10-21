package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

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
@Table(name = "treatments")
public class Treatment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long treatmentId;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date startDate;

  @Temporal(TemporalType.DATE)
  private Date endDate;

  @ManyToMany
  @JoinTable(name = "treatments_mecial_procedures",
          joinColumns = @JoinColumn(name = "treatment_id"),
          inverseJoinColumns = @JoinColumn(name = "medical_procedure_id"))
  private Set<MedicalProcedure> medicalProcedures;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pesel")
  private Patient patient;

}
