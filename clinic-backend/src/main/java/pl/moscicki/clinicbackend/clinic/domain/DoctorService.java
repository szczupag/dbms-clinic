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

  Set<DoctorResponse> getAll(boolean medicalProcedure) {
    return doctorRepository.findAll().stream()
            .map(doctor -> DoctorResponse.from(doctor, medicalProcedure))
            .collect(Collectors.toSet());
  }

  Set<Doctor> getDoctorsByPesel(Set<String> pesels) {
    return doctorRepository.findAllByPeselIn(pesels);
  }

  void createDoctor(CreationDoctor creationDoctor) {
    String supervisorId = Objects.nonNull(creationDoctor.getSupervisorId()) ? creationDoctor.getSupervisorId() : "";

    Doctor doctor = doctorRepository.findById(supervisorId)
            .map(supervisor -> Doctor.from(creationDoctor, supervisor))
            .orElse(Doctor.from(creationDoctor, null));
    doctorRepository.save(doctor);
  }

  void updateDoctor(CreationDoctor creationDoctor, String pesel) {
    String supervisorId = Objects.nonNull(creationDoctor.getSupervisorId()) ? creationDoctor.getSupervisorId() : "";

    Doctor doctor = doctorRepository.findById(supervisorId)
            .map(supervisor -> Doctor.from(creationDoctor, supervisor))
            .orElse(Doctor.from(creationDoctor, null));
    doctor.setPesel(pesel);
    doctorRepository.save(doctor);
  }

  void deleteDoctor(String pesel) {
    doctorRepository.deleteById(pesel);
  }
}
