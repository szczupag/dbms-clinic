package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationClinic;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationLocalization;
import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.ClinicResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.DoctorResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.LocalizationResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.MedicalProcedureResponse;

import java.util.List;
import java.util.Set;

public class ClinicFacade {

  private DoctorService doctorService;
  private MedicalProcedureService medicalProcedureService;
  private ClinicService clinicService;
  private LocalizationService localizationService;
  private DepartmentService departmentService;

  public ClinicFacade(DoctorService doctorService, MedicalProcedureService medicalProcedureService,
                      ClinicService clinicService, LocalizationService localizationService,
                      DepartmentService departmentService) {
    this.doctorService = doctorService;
    this.medicalProcedureService = medicalProcedureService;
    this.clinicService = clinicService;
    this.localizationService = localizationService;
    this.departmentService = departmentService;
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

  public void createClinic(CreationClinic clinic) {
    clinicService.createClinic(clinic, getLocalizationById(clinic.getLocalizationId()),
            getAllDepartmentsByIds(clinic.getDepartmentIds()));
  }

  public void deleteClinic(Long clinicId) {
    clinicService.deleteClinic(clinicId);
  }

  public Clinic getClinicById(Long clinicId) {
    return clinicService.getClinicById(clinicId);
  }

  public Localization getLocalizationById(Long localizationId) {
    return localizationService.getLocalizationById(localizationId);
  }

  public Set<LocalizationResponse> getAllLocalizations(boolean withClinic) {
    return localizationService.getAllLocalizations(withClinic);
  }

  public void createLocalization(CreationLocalization localization) {
    localizationService.createLocalization(localization, getClinicById(localization.getClinicId()));
  }

  public void deleteLocalization(Long localizationId) {
    localizationService.deleteLocalization(localizationId);
  }

  public Set<Department> getAllDepartmentsByIds(Set<Long> ids) {
    return departmentService.getDepartmentsByIds(ids);
  }

}
