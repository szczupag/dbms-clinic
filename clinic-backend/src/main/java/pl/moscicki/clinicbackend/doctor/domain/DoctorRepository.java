package pl.moscicki.clinicbackend.doctor.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface DoctorRepository extends CrudRepository<Doctor, String> {

  @Override
  List<Doctor> findAll();
}
