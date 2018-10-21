package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

interface PatientRepository extends CrudRepository<Patient, String> {
}
