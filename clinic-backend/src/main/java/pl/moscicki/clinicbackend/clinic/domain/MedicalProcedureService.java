package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.MedicalProcedureResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class MedicalProcedureService {
  private MedicalProcedureRepository medicalProcedureRepository;

  public MedicalProcedureService(MedicalProcedureRepository medicalProcedureRepository) {
    this.medicalProcedureRepository = medicalProcedureRepository;
  }

  List<MedicalProcedureResponse> getAllMedicalProcedures() {
    return medicalProcedureRepository.findAll().stream()
            .map(medicalProcedure -> MedicalProcedureResponse.from(medicalProcedure, true, true))
            .collect(Collectors.toList());
  }

  Set<MedicalProcedure> getMedicalProceduresById(Set<Long> medicalProcedureIds) {
    return medicalProcedureRepository.findAllByMedicalProcedureIdIn(medicalProcedureIds);
  }

  void createMedicalProcedure(CreationMedicalProcedure creationMedicalProcedure, Set<Doctor> doctors) {
    MedicalProcedure medicalProcedure = MedicalProcedure.from(creationMedicalProcedure, doctors);
    if (doctors != null) {
      doctors.forEach(doc -> doc.getMedicalProcedures().add(medicalProcedure));
    }
    medicalProcedureRepository.save(medicalProcedure);
  }

  void updateMedicalProcedure(CreationMedicalProcedure creationMedicalProcedure, Set<Doctor> doctors,
                                     Long medicalProcedureId) {

    Optional<MedicalProcedure> old = medicalProcedureRepository.findById(medicalProcedureId);

    old.ifPresent(medicalProcedure -> {
              if (medicalProcedure.getDoctors() != null) {
                medicalProcedure.getDoctors().forEach(doctor -> doctor.getMedicalProcedures().remove(medicalProcedure));
              }
            });

    MedicalProcedure medicalProcedure = MedicalProcedure.from(creationMedicalProcedure, doctors);

    medicalProcedure.setTreatment(old.get().getTreatment());

    if (doctors != null) {
      doctors.forEach(doc -> doc.getMedicalProcedures().add(medicalProcedure));
    }

    medicalProcedure.setMedicalProcedureId(medicalProcedureId);
    medicalProcedureRepository.save(medicalProcedure);
  }

  void deleteMedicalProcedure(Long id) {
    medicalProcedureRepository.deleteById(id);
  }
}
