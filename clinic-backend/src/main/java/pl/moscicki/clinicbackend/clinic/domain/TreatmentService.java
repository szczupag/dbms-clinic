package pl.moscicki.clinicbackend.clinic.domain;

class TreatmentService {
  private TreatmentRepository treatmentRepository;

  public TreatmentService(TreatmentRepository treatmentRepository) {
    this.treatmentRepository = treatmentRepository;
  }
}
