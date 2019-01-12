package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationPatient;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patients")
public class Patient {

  @Id
  @NotNull
  @Column(length = 11)
  private String pesel;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @Size(max = 15)
  private String phoneNumber;

  @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  private Set<Treatment> treatments;

  @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
  private Set<Visit> visits;

  static Patient from(CreationPatient creationPatient, Set<Treatment> treatments, Set<Visit> visits) {
    return Patient.builder()
            .pesel(creationPatient.getPesel())
            .firstName(creationPatient.getFirstName())
            .lastName(creationPatient.getLastName())
            .phoneNumber(creationPatient.getPhoneNumber())
            .visits(visits)
            .treatments(treatments)
            .build();
  }

}
