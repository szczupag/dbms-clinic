package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DoctorResponse;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class DoctorService {
  private DoctorRepository doctorRepository;

  DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }

  Set<DoctorResponse> getAll() {
    return doctorRepository.findAll().stream()
            .map(doctor -> DoctorResponse.from(doctor, true, true, true))
            .collect(Collectors.toSet());
  }

  Set<Doctor> getDoctorsByPesel(Set<String> pesels) {
    return doctorRepository.findAllByPeselIn(pesels);
  }

  Doctor getDoctorByPesel(String pesel) {
    return doctorRepository.findById(pesel).orElse(null);
  }

  void createDoctor(CreationDoctor creationDoctor, Department department) {
    String supervisorId = Objects.nonNull(creationDoctor.getSupervisorId()) ? creationDoctor.getSupervisorId() : "";
    if (supervisorId.equals(creationDoctor.getPesel())) {
      throw new RuntimeException("One can not be it's supervisor");
    }

    Doctor doctor = doctorRepository.findById(supervisorId)
            .map(supervisor -> Doctor.from(creationDoctor, supervisor, department))
            .orElse(Doctor.from(creationDoctor, null, department));
    doctorRepository.save(doctor);
  }

  void updateDoctor(CreationDoctor creationDoctor, String pesel, Department department) {
    String supervisorId = Objects.nonNull(creationDoctor.getSupervisorId()) ? creationDoctor.getSupervisorId() : "";

    Doctor doctor = doctorRepository.findById(supervisorId)
            .map(supervisor -> Doctor.from(creationDoctor, supervisor, department))
            .orElse(Doctor.from(creationDoctor, null, department));
    doctor.setPesel(pesel);
    doctorRepository.save(doctor);
  }

  void deleteDoctor(String pesel) {
    Doctor doctor = doctorRepository.findById(pesel).orElse(null);

    if (doctor != null) {
      doctorRepository.findAll().stream()
              .filter(doc -> doc.getSupervisor() != null && doc.getSupervisor().getPesel().equals(pesel))
              .forEach(empl -> empl.setSupervisor(null));
      doctorRepository.deleteById(pesel);
    }
  }

  void raiseSalary(String pesel) {
    doctorRepository.raise(pesel);
  }
}
