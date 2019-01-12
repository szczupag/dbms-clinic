package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

interface DoctorRepository extends CrudRepository<Doctor, String> {

  @Override
  Set<Doctor> findAll();

  Set<Doctor> findAllByPeselIn(Set<String> doctorsPesels);

}
