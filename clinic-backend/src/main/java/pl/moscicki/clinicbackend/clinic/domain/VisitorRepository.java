package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface VisitorRepository extends CrudRepository<Visitor, String> {

  @Override
  Set<Visitor> findAll();
}
