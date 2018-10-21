package pl.moscicki.clinicbackend.clinic.domain;

class ClinicService {
  private ClinicRepository clinicRepository;

  public ClinicService(ClinicRepository clinicRepository) {
    this.clinicRepository = clinicRepository;
  }
}
