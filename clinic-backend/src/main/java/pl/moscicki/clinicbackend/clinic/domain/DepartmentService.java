package pl.moscicki.clinicbackend.clinic.domain;

class DepartmentService {
  private DepartmentRepository departmentRepository;

  public DepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }
}
