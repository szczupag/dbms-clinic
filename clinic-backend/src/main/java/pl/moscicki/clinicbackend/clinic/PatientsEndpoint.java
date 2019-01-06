package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationPatient;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.PatientResponse;

import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientsEndpoint {

  private ClinicFacade clinicFacade;

  PatientsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<PatientResponse> getAllPatients() {
    return clinicFacade.getAllPatients();
  }

  @PostMapping
  void addPatient(@RequestBody @Validated CreationPatient creationPatient) {
    clinicFacade.createPatient(creationPatient);
  }

  @PutMapping("/{id}")
  void updatePatient(@RequestBody @Validated CreationPatient creationPatient, @PathVariable String id) {
    clinicFacade.updatePatient(creationPatient, id);
  }

  @DeleteMapping("/{id}")
  void deletePatient(@PathVariable String id) {
    clinicFacade.deletePatient(id);
  }
}
