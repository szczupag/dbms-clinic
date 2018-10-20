package pl.moscicki.clinicbackend.doctors.domain;

import lombok.*;
import pl.moscicki.clinicbackend.doctors.domain.dto.CreationDoctor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

  public static Doctor from(CreationDoctor doctor, Doctor supervisor) {
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
