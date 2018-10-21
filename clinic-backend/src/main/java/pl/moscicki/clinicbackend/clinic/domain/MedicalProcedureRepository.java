package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface MedicalProcedureRepository extends CrudRepository<MedicalProcedure, Long> {

  @Override
  List<MedicalProcedure> findAll();
}
