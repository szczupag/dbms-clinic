package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationClinic;
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
            .map(clinic -> ClinicResponse.from(clinic, true, true))
            .collect(Collectors.toSet());
  }

  void createClinic(CreationClinic creationClinic, Localization localization, Set<Department> departments) {
    Clinic clinic = Clinic.from(creationClinic, localization, departments);
    if (localization != null) {
      localization.setClinic(clinic);
    }
    if (departments != null) {
      departments.forEach(department -> department.setClinic(clinic));
    }
    clinicRepository.save(clinic);
  }

  void updateClinic(CreationClinic creationClinic, Localization localization, Set<Department> departments, Long clinicId) {
    Clinic clinic = Clinic.from(creationClinic, localization, departments);
    clinic.setClinicId(clinicId);
    if (localization != null) {
      localization.setClinic(clinic);
    }
    if (departments != null) {
      departments.forEach(department -> department.setClinic(clinic));
    }
    clinicRepository.save(clinic);
  }

  Clinic getClinicById(Long clinicId) {
    return clinicRepository.findById(clinicId).orElse(null);
  }

  void deleteClinic(Long clinicId) {
    clinicRepository.deleteById(clinicId);
  }
}
