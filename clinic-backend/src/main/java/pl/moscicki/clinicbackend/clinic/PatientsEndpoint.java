package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationPatient;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.PatientResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/patients")
public class PatientsEndpoint {

  private ClinicFacade clinicFacade;

  PatientsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  Set<PatientResponse> getAllPatients() {
    return clinicFacade.getAllPatients();
  }

  @CrossOrigin
  @PostMapping
  void addPatient(@RequestBody @Validated CreationPatient creationPatient) {
    clinicFacade.createPatient(creationPatient);
  }

  @CrossOrigin
  @PutMapping("/{pesel}")
  void updatePatient(@RequestBody @Validated CreationPatient creationPatient, @PathVariable String pesel) {
    clinicFacade.updatePatient(creationPatient, pesel);
  }

  @CrossOrigin
  @DeleteMapping("/{pesel}")
  void deletePatient(@PathVariable String pesel) {
    clinicFacade.deletePatient(pesel);
  }

  @CrossOrigin
  @GetMapping("/visitors/count/{pesel}")
  Integer countVisitors(@PathVariable String pesel) {
    return clinicFacade.countVisitors(pesel);
  }
}
