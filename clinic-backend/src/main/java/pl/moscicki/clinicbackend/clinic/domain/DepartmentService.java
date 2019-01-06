package pl.moscicki.clinicbackend.clinic.domain;

import java.util.Set;

class DepartmentService {
  private DepartmentRepository departmentRepository;

  public DepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  Set<Department> getDepartmentsByIds(Set<Long> departmentsIds) {
    return departmentRepository.findAllByDepartmentId(departmentsIds);
  }
}
