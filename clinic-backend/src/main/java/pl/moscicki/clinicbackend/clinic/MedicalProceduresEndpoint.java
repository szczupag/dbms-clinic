package pl.moscicki.clinicbackend.clinic;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.MedicalProcedureResponse;

import java.util.List;

@RestController
@RequestMapping("/medical-procedures")
class MedicalProceduresEndpoint {
  private ClinicFacade clinicFacade;

  public MedicalProceduresEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping(value = "/all")
  List<MedicalProcedureResponse> getAll(@RequestParam(value = "doctors", defaultValue = "false", required = false) boolean withDoctors) {
    return clinicFacade.getAllMedicalProcedures(withDoctors);
  }

  @PutMapping
  @ResponseStatus(value = HttpStatus.OK)
  void createMedicalProcedure(@RequestBody @Validated CreationMedicalProcedure medicalProcedure) {
    clinicFacade.createMedicalProcedure(medicalProcedure);
  }


  @DeleteMapping(value = "/{id}")
  void deleteMedicalProcedure(@PathVariable(value = "id") Long id) {
    clinicFacade.deleteMedicalProcedure(id);
  }

}
