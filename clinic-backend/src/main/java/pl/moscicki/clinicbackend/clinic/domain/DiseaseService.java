package pl.moscicki.clinicbackend.clinic.domain;

class DiseaseService {
  private DiseaseRepository diseaseRepository;

  public DiseaseService(DiseaseRepository diseaseRepository) {
    this.diseaseRepository = diseaseRepository;
  }
}
