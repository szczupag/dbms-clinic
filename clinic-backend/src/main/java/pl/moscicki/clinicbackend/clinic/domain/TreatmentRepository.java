package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface TreatmentRepository extends CrudRepository<Treatment, Long> {

  @Override
  Set<Treatment> findAll();

  Set<Treatment> findAllByTreatmentId(Set<Long> ids);
}
