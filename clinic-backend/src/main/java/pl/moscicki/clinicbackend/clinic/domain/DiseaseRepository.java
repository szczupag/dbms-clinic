package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

interface DiseaseRepository extends CrudRepository<Disease, Long> {
}
