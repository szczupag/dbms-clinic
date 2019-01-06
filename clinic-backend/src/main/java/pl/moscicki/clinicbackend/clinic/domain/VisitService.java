package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.find.VisitResponse;

import java.util.Set;
import java.util.stream.Collectors;

public class VisitService {

  private VisitRepository visitRepository;

  public VisitService(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  Set<VisitResponse> getAllVisits() {
    return visitRepository.findAll().stream()
            .map(visit -> VisitResponse.from(visit, true, true))
            .collect(Collectors.toSet());
  }

  Set<Visit> getVisitsById(Set<Long> ids) {
    return visitRepository.findAllByVisitId(ids);
  }
}
