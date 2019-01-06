package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

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
  Long medicineId;

  @Temporal(TemporalType.DATE)
  Date startDate;

  @OneToOne
  Patient patient;

  @OneToOne
  Visitor visitor;
}
