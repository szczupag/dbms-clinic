package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.TreatmentResponse;

import java.util.Set;
import java.util.stream.Collectors;

class TreatmentService {
  private TreatmentRepository treatmentRepository;

  public TreatmentService(TreatmentRepository treatmentRepository) {
    this.treatmentRepository = treatmentRepository;
  }

  Set<TreatmentResponse> getAllTreatments() {
    return treatmentRepository.findAll().stream()
            .map(treatment -> TreatmentResponse.from(treatment, true, true, true))
            .collect(Collectors.toSet());
  }

  Set<Treatment> getTreatmentsByIds(Set<Long> treatmentIds) {
    return treatmentRepository.findAllByTreatmentId(treatmentIds);
  }

  void createTreatment(CreationTreatment creationTreatment, Set<MedicalProcedure> medicalProcedures, Patient patient,
                       Disease disease) {
    treatmentRepository.save(Treatment.from(creationTreatment, medicalProcedures, patient, disease));
  }

  void updateTreatment(CreationTreatment creationTreatment, Set<MedicalProcedure> medicalProcedures, Patient patient,
                       Disease disease, Long treatmentId) {
    Treatment treatment = Treatment.from(creationTreatment, medicalProcedures, patient, disease);
    treatment.setTreatmentId(treatmentId);

    treatmentRepository.save(treatment);
  }

  void deleteTreatment(Long treatmentId) {
    treatmentRepository.deleteById(treatmentId);
  }
}
