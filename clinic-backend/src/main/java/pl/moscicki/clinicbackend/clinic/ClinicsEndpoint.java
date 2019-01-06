package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationClinic;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.ClinicResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/clinics")
class ClinicsEndpoint {

  private ClinicFacade clinicFacade;

  ClinicsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<ClinicResponse> getAllClinics() {
    return clinicFacade.getAllClinics();
  }

  @PostMapping
  void addClinic(@RequestBody @Validated CreationClinic clinic) {
    clinicFacade.createClinic(clinic);
  }

  @PutMapping("/{id}")
 void updateClinic(@RequestBody @Validated CreationClinic clinic, @PathVariable long id) {
    clinicFacade.updateClinic(clinic, id);
  }

  @DeleteMapping("/{id}")
  void deleteClinic(@PathVariable long id) {
    clinicFacade.deleteClinic(id);
  }
}
