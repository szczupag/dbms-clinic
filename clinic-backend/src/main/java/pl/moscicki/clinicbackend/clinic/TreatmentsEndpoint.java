package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.TreatmentResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentsEndpoint {
  private ClinicFacade clinicFacade;

  public TreatmentsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<TreatmentResponse> getAllTreatments() {
    return clinicFacade.getAllTreatments();
  }

  @PostMapping
  void createTreatment(@RequestBody @Validated CreationTreatment creationTreatment) {
    clinicFacade.createTreatment(creationTreatment);
  }

  @PutMapping("/{id}")
  void updateTreatment(@RequestBody @Validated CreationTreatment creationTreatment, @PathVariable long id) {
    clinicFacade.updateTreatment(creationTreatment, id);
  }

  @DeleteMapping("/{id}")
  void deleteTreatment(@PathVariable long id) {
    clinicFacade.deleteTreatment(id);
  }

}
