package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationLocalization;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.LocalizationResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/localizations")
class LocalizationsEndpoint {
  private ClinicFacade clinicFacade;

  LocalizationsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  Set<LocalizationResponse> getAllLocalizations() {
    return clinicFacade.getAllLocalizations();
  }

  @CrossOrigin
  @PostMapping
  void createLocalization(@RequestBody @Validated CreationLocalization creationLocalization) {
    clinicFacade.createLocalization(creationLocalization);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  void updateLocalization(@RequestBody @Validated CreationLocalization creationLocalization, @PathVariable Long id) {
    clinicFacade.updateLocalization(creationLocalization, id);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  void deleteLocalization(@PathVariable Long id) {
    clinicFacade.deleteLocalization(id);
  }
}
