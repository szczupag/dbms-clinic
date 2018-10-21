package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

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

  @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
  private Set<Treatment> treatments;

  @ManyToMany
  @JoinTable(name = "patients_visitors",
          joinColumns = @JoinColumn(name = "patient_pesel", referencedColumnName = "pesel"),
          inverseJoinColumns = @JoinColumn(name = "visitor_pesel", referencedColumnName = "pesel"))
  private Set<Visitor> visitors;
}
