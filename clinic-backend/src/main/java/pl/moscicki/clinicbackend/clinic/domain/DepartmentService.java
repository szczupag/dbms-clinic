package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDepartment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DepartmentResponse;

import java.util.Set;
import java.util.stream.Collectors;

class DepartmentService {
  private DepartmentRepository departmentRepository;

  public DepartmentService(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  Set<Department> getDepartmentsByIds(Set<Long> departmentsIds) {
    return departmentRepository.findAllByDepartmentId(departmentsIds);
  }

  Set<DepartmentResponse> getAllDepartments() {
    return departmentRepository.findAll().stream()
            .map(DepartmentResponse::from)
            .collect(Collectors.toSet());
  }

  void createDepartment(CreationDepartment creationDepartment, Clinic clinic, Set<Doctor> doctors) {
    Department department = Department.from(creationDepartment, clinic, doctors);

    departmentRepository.save(department);
  }

  void updateDepartment(CreationDepartment creationDepartment, Clinic clinic, Set<Doctor> doctors, long departmentId) {
    Department department = Department.from(creationDepartment, clinic, doctors);
    department.setDepartmentId(departmentId);

    departmentRepository.save(department);
  }

  void deleteDepartment(Long departmentId) {
    departmentRepository.deleteById(departmentId);
  }
}
