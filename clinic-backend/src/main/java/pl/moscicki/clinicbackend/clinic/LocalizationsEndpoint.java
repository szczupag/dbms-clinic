package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationLocalization;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.LocalizationResponse;

import java.util.Set;

@RestController
@RequestMapping("/localizations")
class LocalizationsEndpoint {
  private ClinicFacade clinicFacade;

  LocalizationsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<LocalizationResponse> getAllLocalizations(@RequestParam(name = "withClinic", defaultValue = "false", required = false) boolean withClinic) {
    return clinicFacade.getAllLocalizations(withClinic);
  }

  @PostMapping
  void createLocalization(@RequestBody @Validated CreationLocalization creationLocalization) {
    clinicFacade.createLocalization(creationLocalization);
  }

  @DeleteMapping
  void deleteLocalization(@RequestBody @Validated Long localizationId) {
    clinicFacade.deleteLocalization(localizationId);
  }
}
