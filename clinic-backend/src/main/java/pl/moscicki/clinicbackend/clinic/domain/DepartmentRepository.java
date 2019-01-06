package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface DepartmentRepository extends CrudRepository<Department, Long> {
  @Override
  Set<Department> findAll();

  Set<Department> findAllByDepartmentId(Set<Long> ids);
}
