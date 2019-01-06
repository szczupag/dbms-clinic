package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisitor;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.VisitResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.VisitorResponse;

import java.util.Set;
import java.util.stream.Collectors;

class VisitorService {
  private VisitorRepository visitorRepository;

  public VisitorService(VisitorRepository visitorRepository) {
    this.visitorRepository = visitorRepository;
  }

  Set<VisitorResponse> getAllVisistors() {
    return visitorRepository.findAll().stream()
            .map(visitor -> VisitorResponse.from(visitor, false))
            .collect(Collectors.toSet());
  }

  void createVisitor(CreationVisitor creationVisitor, Set<Visit> visits) {
    visitorRepository.save(Visitor.from(creationVisitor, visits));
  }

  void updateVisitor(CreationVisitor creationVisitor, Set<Visit> visits, String visitorPesel) {
    Visitor visitor = Visitor.from(creationVisitor, visits);
    visitor.setPesel(visitorPesel);

    visitorRepository.save(visitor);
  }

  void deleteVisitor(String visitorPesel) {
    visitorRepository.deleteById(visitorPesel);
  }


}

