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

  @CrossOrigin
  @GetMapping(value = "/all")
  List<MedicalProcedureResponse> getAll() {
    return clinicFacade.getAllMedicalProcedures();
  }

  @CrossOrigin
  @PostMapping
  void createMedicalProcedure(@RequestBody @Validated CreationMedicalProcedure medicalProcedure) {
    clinicFacade.createMedicalProcedure(medicalProcedure);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  void updateMedicalProcedure(@RequestBody @Validated CreationMedicalProcedure medicalProcedure, @PathVariable Long id) {
    clinicFacade.updateMedicalProcedure(medicalProcedure, id);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  void deleteMedicalProcedure(@PathVariable Long id) {
    clinicFacade.deleteMedicalProcedure(id);
  }

}
