package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.TreatmentResponse;

import java.util.Optional;
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
    return treatmentRepository.findAllByTreatmentIdIn(treatmentIds);
  }

  void createTreatment(CreationTreatment creationTreatment, Set<MedicalProcedure> medicalProcedures, Patient patient,
                       Disease disease) {
    Treatment treatment = Treatment.from(creationTreatment, medicalProcedures, patient, disease);

    if (medicalProcedures != null) {
      medicalProcedures.forEach(medicalProcedure -> medicalProcedure.setTreatment(treatment));
    }

    treatmentRepository.save(treatment);
  }

  void updateTreatment(CreationTreatment creationTreatment, Set<MedicalProcedure> medicalProcedures, Patient patient,
                       Disease disease, Long treatmentId) {

    Optional<Treatment> old = treatmentRepository.findById(treatmentId);

    old.ifPresent(trt -> {
      trt.getMedicalProcedures().stream().forEach(md -> md.setTreatment(null));
    });

    Treatment treatment = Treatment.from(creationTreatment, medicalProcedures, patient, disease);

    if (medicalProcedures != null) {
      medicalProcedures.forEach(md2 -> md2.setTreatment(treatment));
    }

    treatment.setTreatmentId(treatmentId);


    treatmentRepository.save(treatment);
  }

  void deleteTreatment(Long treatmentId) {
    treatmentRepository.deleteById(treatmentId);
  }
}
