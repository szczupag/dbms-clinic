package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisit;
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

  void createVisit(CreationVisit creationVisit, Patient patient, Visitor visitor) {
    visitRepository.save(Visit.from(creationVisit, patient, visitor));
  }

  void updateVisit(CreationVisit creationVisit, Patient patient, Visitor visitor, Long visitId) {
    Visit visit = Visit.from(creationVisit, patient, visitor);
    visit.setVisitId(visitId);

    visitRepository.save(visit);
  }

  void deleteVisit(Long visitId) {
    visitRepository.deleteById(visitId);
  }
}
