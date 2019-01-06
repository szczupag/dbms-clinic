package pl.moscicki.clinicbackend.clinic;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.clinic.domain.ClinicFacade;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDepartment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DepartmentResponse;

import java.util.Set;

@RestController
@RequestMapping("/departments")
class DepartmentsEndpoint {
  private ClinicFacade clinicFacade;

  public DepartmentsEndpoint(ClinicFacade clinicFacade) {
    this.clinicFacade = clinicFacade;
  }

  @GetMapping("/all")
  Set<DepartmentResponse> getAllDepartments() {
    return clinicFacade.getAllDepartments();
  }

  @PostMapping
  void createDepartment(@RequestBody @Validated CreationDepartment creationDepartment) {
    clinicFacade.createDepartment(creationDepartment);
  }

  @PutMapping("/{id}")
  void updateDepartment(@RequestBody @Validated CreationDepartment creationDepartment) {

  }

  @DeleteMapping("/{id}")
  void deleteDepartment(@PathVariable Long departmentId) {
    clinicFacade.deleteDepartment(departmentId);
  }
}
