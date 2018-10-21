package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.find.ClinicResponse;

import java.util.Set;
import java.util.stream.Collectors;

class ClinicService {
  private ClinicRepository clinicRepository;

  public ClinicService(ClinicRepository clinicRepository) {
    this.clinicRepository = clinicRepository;
  }

  Set<ClinicResponse> getAllClinics() {
    return clinicRepository.findAll().stream()
            .map(ClinicResponse::from)
            .collect(Collectors.toSet());
  }
}
