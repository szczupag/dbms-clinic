package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

interface MedicineRepository extends CrudRepository<Medicine, Long> {
}
