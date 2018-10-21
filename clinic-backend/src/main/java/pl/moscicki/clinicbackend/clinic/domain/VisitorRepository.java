package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;

interface VisitorRepository extends CrudRepository<Visitor, String> {
}
