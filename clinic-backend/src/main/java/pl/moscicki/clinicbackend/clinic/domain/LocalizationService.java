package pl.moscicki.clinicbackend.clinic.domain;

class LocalizationService {
  private LocalizationRepository localizationRepository;

  public LocalizationService(LocalizationRepository localizationRepository) {
    this.localizationRepository = localizationRepository;
  }
}
