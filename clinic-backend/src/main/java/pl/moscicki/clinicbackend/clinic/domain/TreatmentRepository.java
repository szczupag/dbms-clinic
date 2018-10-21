package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

interface TreatmentRepository extends CrudRepository<Treatment, Long> {
}
