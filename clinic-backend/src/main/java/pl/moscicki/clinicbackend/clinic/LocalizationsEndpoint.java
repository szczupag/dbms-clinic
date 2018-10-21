package pl.moscicki.clinicbackend.clinic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
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

}
