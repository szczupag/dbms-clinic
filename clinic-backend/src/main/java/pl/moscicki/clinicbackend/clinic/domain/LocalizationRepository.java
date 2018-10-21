package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface LocalizationRepository extends CrudRepository<Localization, Long> {

  @Override
  Set<Localization> findAll();
}
