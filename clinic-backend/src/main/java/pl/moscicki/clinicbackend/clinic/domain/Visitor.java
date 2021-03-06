package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisitor;

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
@Table(name = "visitors")
public class Visitor {
  @Id
  @NotNull()
  @Column(length = 11)
  String pesel;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Size(max = 7)
  private String idNumber;

  @OneToMany(mappedBy = "visitor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Visit> visits;

  static Visitor from(CreationVisitor creationVisitor, Set<Visit> visits) {
    return Visitor.builder()
            .pesel(creationVisitor.getPesel())
            .firstName(creationVisitor.getFirstName())
            .lastName(creationVisitor.getLastName())
            .idNumber(creationVisitor.getIdNumber())
            .visits(visits)
            .build();
  }
}
