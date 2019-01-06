package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDisease;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DiseaseResponse;

import java.util.Set;
import java.util.stream.Collectors;

class DiseaseService {
  private DiseaseRepository diseaseRepository;

  public DiseaseService(DiseaseRepository diseaseRepository) {
    this.diseaseRepository = diseaseRepository;
  }

  Set<DiseaseResponse> getAllDiseases() {
    return diseaseRepository.findAll().stream()
            .map(disease -> DiseaseResponse.from(disease,false))
            .collect(Collectors.toSet());
  }

  Disease getDiseaseById(Long diseaseId) {
    return diseaseRepository.findById(diseaseId).orElse(null);
  }

  void createDisease(CreationDisease creationDisease, Set<Treatment> treatments) {
    diseaseRepository.save(Disease.from(creationDisease, treatments));
  }

  void updateDisease(CreationDisease creationDisease, Set<Treatment> treatments, Long diseaseId) {
    Disease disease = Disease.from(creationDisease, treatments);
    disease.setDiseaseId(diseaseId);

    diseaseRepository.save(disease);
  }

  void deleteDisease(Long diseaseId) {
    diseaseRepository.deleteById(diseaseId);
  }
}
