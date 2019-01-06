package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.MedicalProcedureResponse;

import java.util.List;

@RestController
@RequestMapping("/api/medical-procedures")
class MedicalProceduresEndpoint {
  private ClinicFacade clinicFacade;

  public MedicalProceduresEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping(value = "/all")
  List<MedicalProcedureResponse> getAll(@RequestParam(value = "doctors", defaultValue = "false", required = false) boolean withDoctors) {
    return clinicFacade.getAllMedicalProcedures(withDoctors);
  }

  @PostMapping
  void createMedicalProcedure(@RequestBody @Validated CreationMedicalProcedure medicalProcedure) {
    clinicFacade.createMedicalProcedure(medicalProcedure);
  }

  @PutMapping("/{id}")
  void updateMedicalProcedure(@RequestBody @Validated CreationMedicalProcedure medicalProcedure, @PathVariable Long id) {
    clinicFacade.updateMedicalProcedure(medicalProcedure, id);
  }


  @DeleteMapping("/{id}")
  void deleteMedicalProcedure(@PathVariable Long id) {
    clinicFacade.deleteMedicalProcedure(id);
  }

}
