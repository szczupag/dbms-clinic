package pl.moscicki.clinicbackend.clinic.domain;

class VisitorService {
  private VisitorRepository visitorRepository;

  public VisitorService(VisitorRepository visitorRepository) {
    this.visitorRepository = visitorRepository;
  }
}
