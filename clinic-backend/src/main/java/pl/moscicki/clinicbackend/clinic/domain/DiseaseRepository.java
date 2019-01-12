package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface DiseaseRepository extends CrudRepository<Disease, Long> {

  @Override
  Set<Disease> findAll();
}
