package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDepartment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DepartmentResponse;

import java.util.Set;

@RestController
@RequestMapping("/api/departments")
class DepartmentsEndpoint {
  private ClinicFacade clinicFacade;

  public DepartmentsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @CrossOrigin
  @GetMapping("/all")
  Set<DepartmentResponse> getAllDepartments() {
    return clinicFacade.getAllDepartments();
  }

  @CrossOrigin
  @PostMapping
  void createDepartment(@RequestBody @Validated CreationDepartment creationDepartment) {
    clinicFacade.createDepartment(creationDepartment);
  }

  @CrossOrigin
  @PutMapping("/{id}")
  void updateDepartment(@RequestBody @Validated CreationDepartment creationDepartment, @PathVariable long id) {
    clinicFacade.updateDepartment(creationDepartment, id);
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  void deleteDepartment(@PathVariable Long id) {
    clinicFacade.deleteDepartment(id);
  }
}
