package pl.moscicki.clinicbackend.clinic;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.TreatmentResponse;

import java.util.List;

@RestController
@RequestMapping("/treatments")
class TreatmentsEndpoint {
  private ClinicFacade clinicFacade;

  public TreatmentsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping(value = "/all")
  List<TreatmentResponse> getAll(@RequestParam(value = "doctors", defaultValue = "false", required = false) boolean withDoctors) {
    return clinicFacade.getAllTreatments(withDoctors);
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.OK)
  void createTreatment(@RequestBody @Validated CreationTreatment treatment) {
    clinicFacade.createTreatment(treatment);
  }


  @DeleteMapping(value = "/{id}")
  void deleteDoctor(@PathVariable(value = "id") Long id) {
    clinicFacade.deleteTreatment(id);
  }

}
