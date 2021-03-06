package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDisease;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DiseaseResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/diseases")
public class DiseasesEndpoint {
  private ClinicFacade clinicFacade;

  public DiseasesEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  Set<DiseaseResponse> getAllDiseases() {
    return clinicFacade.getAllDiseases();
  }

  @CrossOrigin
  @PostMapping
  void createDisease(@RequestBody @Validated CreationDisease creationDisease) {
    clinicFacade.createDisease(creationDisease);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  void updateDisease(@RequestBody @Validated CreationDisease creationDisease, @PathVariable Long id) {
    clinicFacade.updateDisease(creationDisease, id);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  void deleteDisease(@PathVariable Long id) {
    clinicFacade.deleteDisease(id);
  }
}
