package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisit;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "visits")
public class Visit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long visitId;

  @Temporal(TemporalType.DATE)
  Date visitDate;

  @ManyToOne
  @JoinColumn(name = "pesel")
  Patient patient;

  @ManyToOne
  @JoinColumn(name = "pesel")
  Visitor visitor;

  static Visit from(CreationVisit creationVisit, Patient patient, Visitor visitor) {
    return Visit.builder()
            .visitDate(creationVisit.getVisitDate())
            .patient(patient)
            .visitor(visitor)
            .build();
  }
}
