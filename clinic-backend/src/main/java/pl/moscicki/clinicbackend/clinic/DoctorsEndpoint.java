package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DoctorResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/doctors")
class DoctorsEndpoint {

  private ClinicFacade clinicFacade;

  DoctorsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping(value = "/all")
  Set<DoctorResponse> getAll(@RequestParam(value = "medical-procedures", defaultValue = "false", required = false) boolean withMedicalProcedures) {
    return clinicFacade.getAllDoctors(withMedicalProcedures);
  }

  @PostMapping
  void createDoctor(@RequestBody @Validated CreationDoctor doctor) {
    clinicFacade.createDoctor(doctor);
  }

  @PutMapping("/{pesel}")
  void updateDoctor(@RequestBody @Validated CreationDoctor creationDoctor, @PathVariable String pesel) {
    clinicFacade.updateDoctor(creationDoctor, pesel);
  }

  @DeleteMapping("/{pesel}")
  void deleteDoctor(@PathVariable String pesel) {
    clinicFacade.deleteDoctor(pesel);
  }

}
