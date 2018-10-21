package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long departmentId;

  @NotNull
  private String name;

  @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
  private Set<Doctor> doctors;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "clinic_id")
  private Clinic clinic;
}
