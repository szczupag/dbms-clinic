package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.MedicalProcedureResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class MedicalProcedureService {
  private MedicalProcedureRepository medicalProcedureRepository;

  public MedicalProcedureService(MedicalProcedureRepository medicalProcedureRepository) {
    this.medicalProcedureRepository = medicalProcedureRepository;
  }

  List<MedicalProcedureResponse> getAllMedicalProcedures(boolean withDoctors) {
    return medicalProcedureRepository.findAll().stream()
            .map(medicalProcedure -> MedicalProcedureResponse.from(medicalProcedure, withDoctors))
            .collect(Collectors.toList());
  }

  void createMedicalProcedure(CreationMedicalProcedure creationMedicalProcedure, Set<Doctor> doctors) {
    medicalProcedureRepository.save(MedicalProcedure.from(creationMedicalProcedure, doctors));
  }

  void deleteMedicalProcedure(Long id) {
    medicalProcedureRepository.deleteById(id);
  }
}
