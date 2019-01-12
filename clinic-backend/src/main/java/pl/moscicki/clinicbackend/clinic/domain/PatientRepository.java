package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface PatientRepository extends CrudRepository<Patient, String> {

  @Override
  Set<Patient> findAll();
}
