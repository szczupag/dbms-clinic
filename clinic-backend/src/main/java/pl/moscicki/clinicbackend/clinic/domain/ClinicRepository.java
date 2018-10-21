package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.repository.CrudRepository;
import pl.moscicki.clinicbackend.clinic.domain.Clinic;

public interface ClinicRepository extends CrudRepository<Clinic, Long> {
}
