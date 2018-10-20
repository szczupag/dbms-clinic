package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.TreatmentResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TreatmentService {
  private TreatmentRepository treatmentRepository;

  public TreatmentService(TreatmentRepository treatmentRepository) {
    this.treatmentRepository = treatmentRepository;
  }

  List<TreatmentResponse> getAllTreatments(boolean withDoctors) {
    return treatmentRepository.findAll().stream()
            .map(treatment -> TreatmentResponse.from(treatment, withDoctors))
            .collect(Collectors.toList());
  }

  void createTreatment(CreationTreatment creationTreatment, Set<Doctor> doctors) {
    treatmentRepository.save(Treatment.from(creationTreatment, doctors));
  }

  void deleteTreatment(Long id) {
    treatmentRepository.deleteById(id);
  }
}
