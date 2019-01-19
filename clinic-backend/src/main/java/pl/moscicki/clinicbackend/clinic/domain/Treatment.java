package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationTreatment;

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

  @OneToMany(mappedBy = "treatment", fetch = FetchType.LAZY, cascade = {
          CascadeType.PERSIST, CascadeType.MERGE
  })
  private Set<MedicalProcedure> medicalProcedures;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
          CascadeType.PERSIST, CascadeType.MERGE
  })
  @JoinColumn(name = "pesel")
  private Patient patient;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {
          CascadeType.PERSIST, CascadeType.MERGE
  })
  @JoinColumn(name = "disease_id")
  private Disease disease;

  static Treatment from(CreationTreatment treatment, Set<MedicalProcedure> medicalProcedures, Patient patient, Disease disease) {
    return Treatment.builder()
            .startDate(treatment.getStartDate())
            .endDate(treatment.getEndDate())
            .medicalProcedures(medicalProcedures)
            .patient(patient)
            .disease(disease)
            .build();
  }


}
