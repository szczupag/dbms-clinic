package pl.moscicki.clinicbackend.clinic.domain;


import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationLocalization;

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

  private Long buildingNo;

  @OneToOne
  private Clinic clinic;

  static Localization from(CreationLocalization creationLocalization, Clinic clinic) {
    return Localization.builder()
            .city(creationLocalization.getCity())
            .street(creationLocalization.getStreet())
            .postalCode(creationLocalization.getPostalCode())
            .buildingNo(creationLocalization.getBuildingNo())
            .clinic(clinic)
            .build();
  }
}
