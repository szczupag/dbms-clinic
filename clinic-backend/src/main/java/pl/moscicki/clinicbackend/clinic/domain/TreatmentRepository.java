package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface TreatmentRepository extends CrudRepository<Treatment, Long> {

  @Override
  List<Treatment> findAll();
}
