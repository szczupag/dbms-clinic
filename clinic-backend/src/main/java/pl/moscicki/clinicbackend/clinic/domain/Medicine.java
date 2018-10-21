package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "medicines")
public class Medicine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long medicineId;

  @NotNull
  String name;

  @NotNull
  Long recommendedDose;

  @NotNull
  Long cost;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "disease_id")
  private Disease disease;
}
