package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ClinicRepository extends CrudRepository<Clinic, Long> {

  @Override
  Set<Clinic> findAll();
}
