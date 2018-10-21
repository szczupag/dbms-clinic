package pl.moscicki.clinicbackend.clinic.domain;

import lombok.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationDoctor;

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
@Table(name = "doctors")
public class Doctor {
  @Id
  @Size(min = 11, max = 11)
  @NotNull
  private String pesel;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  private Long salary;

  @NotNull
  private String speciality;

  @OneToOne
  private Doctor supervisor;

  @ManyToMany
  @JoinTable(name = "doctors_medical_procedures",
          joinColumns = @JoinColumn(name = "pesel"),
          inverseJoinColumns = @JoinColumn(name = "medical_procedure_id"))
  private Set<MedicalProcedure> medicalProcedures;

  static Doctor from(CreationDoctor doctor, Doctor supervisor) {
    return Doctor.builder()
            .pesel(doctor.getPesel())
            .firstName(doctor.getFirstName())
            .lastName(doctor.getLastName())
            .salary(doctor.getSalary())
            .speciality(doctor.getSpeciality())
            .supervisor(supervisor)
            .build();
  }
}
