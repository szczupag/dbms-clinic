package pl.moscicki.clinicbackend.clinic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.ClinicResponse;

import java.util.Set;

@RestController
@RequestMapping("/clinics")
class ClinicsEndpoint {

  private ClinicFacade clinicFacade;

  public ClinicsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<ClinicResponse> getAllClinics() {
    return clinicFacade.getAllClinics();
  }
}
