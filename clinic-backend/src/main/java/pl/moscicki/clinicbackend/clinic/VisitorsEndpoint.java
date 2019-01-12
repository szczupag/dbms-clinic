package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationVisitor;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.VisitorResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/visitors")
public class VisitorsEndpoint {
  private ClinicFacade clinicFacade;

  public VisitorsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  Set<VisitorResponse> getAllVisitors() {
    return clinicFacade.getAllVisitors();
  }

  @CrossOrigin
  @PostMapping
  void addVisitor(@RequestBody @Validated CreationVisitor creationVisitor) {
    clinicFacade.createVisitor(creationVisitor);
  }

  @CrossOrigin
  @PutMapping("/{pesel}")
  void updateVisitor(@RequestBody @Validated CreationVisitor creationVisitor, @PathVariable String pesel) {
    clinicFacade.updateVisitor(creationVisitor, pesel);
  }

  @CrossOrigin
  @DeleteMapping("/{pesel}")
  void deleteVisitor(@PathVariable String pesel) {
    clinicFacade.deleteVisitor(pesel);
  }
}
