package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationPatient;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.PatientResponse;

import java.util.Set;
import java.util.stream.Collectors;

class PatientService {
  private PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  Patient getPatientByPesel(String pesel) {
    return patientRepository.findById(pesel).orElse(null);
  }

  Set<PatientResponse> getAllPatients() {
    return patientRepository.findAll().stream()
            .map(patient -> PatientResponse.from(patient, false))
            .collect(Collectors.toSet());
  }

  void createPatient(CreationPatient creationPatient, Set<Treatment> treatments, Set<Visit> visits) {
    patientRepository.save(Patient.from(creationPatient, treatments, visits));
  }

  void updatePatient(CreationPatient creationPatient, Set<Treatment> treatments, Set<Visit> visits, String pesel) {
    Patient patient = Patient.from(creationPatient, treatments, visits);
    patient.setPesel(pesel);

    patientRepository.save(patient);
  }

  void deletePatient(String pesel) {
    patientRepository.deleteById(pesel);
  }

}
