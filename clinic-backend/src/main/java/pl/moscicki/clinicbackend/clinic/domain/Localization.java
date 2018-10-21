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
@Table(name = "localizations")
public class Localization {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long localizationId;

  @NotNull
  private String city;

  @NotNull
  private String street;

  @NotNull
  private String postalCode;

  @NotNull
  private String buildingNo;

  @OneToOne
  private Clinic clinic;
}
