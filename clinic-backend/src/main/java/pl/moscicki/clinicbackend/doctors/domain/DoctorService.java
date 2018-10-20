package pl.moscicki.clinicbackend.doctors.domain;

import pl.moscicki.clinicbackend.doctors.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.doctors.domain.dto.DoctorResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class DoctorService {
  private DoctorRepository doctorRepository;

  DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }

  List<DoctorResponse> getAll() {
    return doctorRepository.findAll().stream()
            .map(DoctorResponse::from)
            .collect(Collectors.toList());
  }

  void createDoctor(CreationDoctor creationDoctor) {
    String supervisorId = Objects.nonNull(creationDoctor.getSupervisorId()) ? creationDoctor.getSupervisorId() : "";

    Doctor doctor = doctorRepository.findById(supervisorId)
            .map(supervisor -> Doctor.from(creationDoctor, supervisor))
            .orElse(Doctor.from(creationDoctor, null));
    doctorRepository.save(doctor);
  }
}
