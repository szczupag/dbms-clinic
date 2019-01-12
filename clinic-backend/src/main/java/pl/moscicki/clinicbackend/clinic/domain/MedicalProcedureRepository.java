package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

interface MedicalProcedureRepository extends CrudRepository<MedicalProcedure, Long> {

  @Override
  List<MedicalProcedure> findAll();

  Set<MedicalProcedure> findAllByMedicalProcedureId(Set<Long> ids);
}
