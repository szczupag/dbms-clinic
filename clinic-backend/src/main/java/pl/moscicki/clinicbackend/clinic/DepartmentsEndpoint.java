package pl.moscicki.clinicbackend.clinic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;

@RestController
@RequestMapping("/departments")
class DepartmentsEndpoint {
  private ClinicFacade clinicFacade;

  public DepartmentsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }
}
