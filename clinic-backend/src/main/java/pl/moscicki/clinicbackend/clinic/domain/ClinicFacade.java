package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.ClinicResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DoctorResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.MedicalProcedureResponse;

import java.util.List;
import java.util.Set;

public class ClinicFacade {

  private DoctorService doctorService;
  private MedicalProcedureService medicalProcedureService;
  private ClinicService clinicService;
  private LocalizationService localizationService;

  public ClinicFacade(DoctorService doctorService, MedicalProcedureService medicalProcedureService,
                      ClinicService clinicService, LocalizationService localizationService) {
    this.doctorService = doctorService;
    this.medicalProcedureService = medicalProcedureService;
    this.clinicService = clinicService;
    this.localizationService = localizationService;
  }

  public Set<DoctorResponse> getAllDoctors(boolean withMedicalProcedures) {
    return doctorService.getAll(withMedicalProcedures);
  }

  public void createDoctor(CreationDoctor doctor) {
    doctorService.createDoctor(doctor);
  }

  public void deleteDoctor(String pesel) {
    doctorService.deleteDoctor(pesel);
  }

  private Set<Doctor> getDoctorsByPesels(Set<String> pesels) {
    return doctorService.getDoctorsByPesel(pesels);
  }

  public List<MedicalProcedureResponse> getAllMedicalProcedures(boolean withDoctors) {
    return medicalProcedureService.getAllMedicalProcedures(withDoctors);
  }

  public void createMedicalProcedure(CreationMedicalProcedure medicalProcedure) {
    medicalProcedureService.createMedicalProcedure(medicalProcedure, getDoctorsByPesels(medicalProcedure.getDoctorsIds()));
  }

  public void deleteMedicalProcedure(Long id) {
    medicalProcedureService.deleteMedicalProcedure(id);
  }

  public Set<ClinicResponse> getAllClinics() {
    return clinicService.getAllClinics();
  }

}
