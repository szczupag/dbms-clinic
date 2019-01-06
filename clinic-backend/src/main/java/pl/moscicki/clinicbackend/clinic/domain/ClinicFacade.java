package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.*;
import pl.moscicki.clinicbackend.clinic.domain.dto.find.*;

import java.util.List;
import java.util.Set;

public class ClinicFacade {

  private DoctorService doctorService;
  private MedicalProcedureService medicalProcedureService;
  private ClinicService clinicService;
  private LocalizationService localizationService;
  private DepartmentService departmentService;
  private DiseaseService diseaseService;
  private TreatmentService treatmentService;
  private PatientService patientService;
  private VisitService visitService;

  public ClinicFacade(DoctorService doctorService, MedicalProcedureService medicalProcedureService,
                      ClinicService clinicService, LocalizationService localizationService,
                      DepartmentService departmentService, DiseaseService diseaseService,
                      TreatmentService treatmentService, PatientService patientService,
                      VisitService visitService) {
    this.doctorService = doctorService;
    this.medicalProcedureService = medicalProcedureService;
    this.clinicService = clinicService;
    this.localizationService = localizationService;
    this.departmentService = departmentService;
    this.diseaseService = diseaseService;
    this.treatmentService = treatmentService;
    this.patientService = patientService;
    this.visitService = visitService;
  }

  public Set<DoctorResponse> getAllDoctors(boolean withMedicalProcedures) {
    return doctorService.getAll(withMedicalProcedures);
  }

  public void createDoctor(CreationDoctor doctor) {
    doctorService.createDoctor(doctor);
  }

  public void updateDoctor(CreationDoctor doctor, String pesel) {
    doctorService.updateDoctor(doctor, pesel);
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

  public Set<MedicalProcedure> getMedicalProceduresById(Set<Long> medicalProcedureIds) {
    return medicalProcedureService.getMedicalProceduresById(medicalProcedureIds);
  }

  public void createMedicalProcedure(CreationMedicalProcedure medicalProcedure) {
    medicalProcedureService.createMedicalProcedure(medicalProcedure, getDoctorsByPesels(medicalProcedure.getDoctorsIds()));
  }

  public void updateMedicalProcedure(CreationMedicalProcedure medicalProcedure, Long medicalProcedureId) {
    medicalProcedureService.updateMedicalProcedure(medicalProcedure, getDoctorsByPesels(medicalProcedure.getDoctorsIds()),
            medicalProcedureId);
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

  public void updateClinic(CreationClinic creationClinic, long clinicId) {
    clinicService.updateClinic(creationClinic, getLocalizationById(creationClinic.getLocalizationId()),
            getAllDepartmentsByIds(creationClinic.getDepartmentIds()), clinicId);
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

  public void updateLocalization(CreationLocalization localization, Long localizationId) {
    localizationService.updateLocalization(localization, getClinicById(localization.getClinicId()), localizationId);
  }

  public Set<Department> getAllDepartmentsByIds(Set<Long> ids) {
    return departmentService.getDepartmentsByIds(ids);
  }

  public Set<DepartmentResponse> getAllDepartments() {
    return departmentService.getAllDepartments();
  }

  public void deleteDepartment(Long departmentId) {
    departmentService.deleteDepartment(departmentId);
  }

  public void createDepartment(CreationDepartment department) {
    departmentService.createDepartment(department, getClinicById(department.getClinicId()),
            getDoctorsByPesels(department.getDoctorsPesels()));
  }

  public void updateDepartment(CreationDepartment department, long departmentId) {
    departmentService.updateDepartment(department, getClinicById(department.getClinicId()),
            getDoctorsByPesels(department.getDoctorsPesels()), departmentId);
  }

  public Set<DiseaseResponse> getAllDiseases() {
    return diseaseService.getAllDiseases();
  }

  private Disease getDiseaseById(long diseaseId) {
    return diseaseService.getDiseaseById(diseaseId);
  }

  public Set<TreatmentResponse> getAllTreatments() {
    return treatmentService.getAllTreatments();
  }

  private Set<Treatment> getTreatmentsByIds(Set<Long> treatmentIds) {
    return treatmentService.getTreatmentsByIds(treatmentIds);
  }

  public void createTreatment(CreationTreatment treatment) {
    treatmentService.createTreatment(treatment, getMedicalProceduresById(treatment.getMedicalProceduresIds()),
            getPatientByPesel(treatment.getPatientPesel()), getDiseaseById(treatment.getDiseaseId()));
  }

  public void updateTreatment(CreationTreatment treatment, long treatmentId) {
    treatmentService.updateTreatment(treatment, getMedicalProceduresById(treatment.getMedicalProceduresIds()),
            getPatientByPesel(treatment.getPatientPesel()), getDiseaseById(treatment.getDiseaseId()), treatmentId);
  }

  public void deleteTreatment(long treatmentId) {
    treatmentService.deleteTreatment(treatmentId);
  }

  private Patient getPatientByPesel(String pesel) {
    return patientService.getPatientByPesel(pesel);
  }

  public Set<PatientResponse> getAllPatients() {
    return patientService.getAllPatients();
  }

  public void createPatient(CreationPatient creationPatient) {
    patientService.createPatient(creationPatient, getTreatmentsByIds(creationPatient.getTreatmentIds()),
            getVisitsByIds(creationPatient.getVisitIds()));
  }

  public void updatePatient(CreationPatient creationPatient, String pesel) {
    patientService.updatePatient(creationPatient, getTreatmentsByIds(creationPatient.getTreatmentIds()),
            getVisitsByIds(creationPatient.getVisitIds()), pesel);
  }

  public void deletePatient(String pesel) {
    patientService.deletePatient(pesel);
  }

  private Set<Visit> getVisitsByIds(Set<Long> ids) {
    return visitService.getVisitsById(ids);
  }

}
