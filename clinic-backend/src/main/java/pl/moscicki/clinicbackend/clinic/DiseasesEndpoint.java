package pl.moscicki.clinicbackend.clinic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DiseaseResponse;

import java.util.Set;

@RestController
@RequestMapping("/deseases")
public class DiseasesEndpoint {
  private ClinicFacade clinicFacade;

  public DiseasesEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<DiseaseResponse> getAllDiseases() {
    return clinicFacade.getAllDiseases();
  }

//  @PostMapping("")

}
