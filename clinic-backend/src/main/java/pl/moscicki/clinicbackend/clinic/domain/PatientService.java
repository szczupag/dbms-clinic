package pl.moscicki.clinicbackend.clinic.domain;

class PatientService {
  private PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }
}
