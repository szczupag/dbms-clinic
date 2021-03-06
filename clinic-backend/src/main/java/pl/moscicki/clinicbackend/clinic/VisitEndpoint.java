package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisit;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.VisitResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/visits")
public class VisitEndpoint {
  private ClinicFacade clinicFacade;

  public VisitEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  public Set<VisitResponse> getAllVisits() {
    return clinicFacade.getAllVisits();
  }

  @CrossOrigin
  @PostMapping
  public void createVisit(@RequestBody @Validated CreationVisit creationVisit) {
    clinicFacade.createVisit(creationVisit);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  public void updateVisit(@RequestBody @Validated CreationVisit creationVisit, @PathVariable Long id) {
    clinicFacade.updateVisit(creationVisit, id);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  public void deleteVisit(@PathVariable Long id) {
    clinicFacade.deleteVisit(id);
  }
}
