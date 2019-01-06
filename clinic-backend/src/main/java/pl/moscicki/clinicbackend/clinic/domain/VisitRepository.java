package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface VisitRepository extends CrudRepository<Visit, Long> {

  @Override
  Set<Visit> findAll();

  Set<Visit> findAllByVisitId(Set<Long> id);
}
