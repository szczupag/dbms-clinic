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
  private VisitorService visitorService;

  public ClinicFacade(DoctorService doctorService, MedicalProcedureService medicalProcedureService,
                      ClinicService clinicService, LocalizationService localizationService,
                      DepartmentService departmentService, DiseaseService diseaseService,
                      TreatmentService treatmentService, PatientService patientService,
                      VisitService visitService, VisitorService visitorService) {
    this.doctorService = doctorService;
    this.medicalProcedureService = medicalProcedureService;
    this.clinicService = clinicService;
    this.localizationService = localizationService;
    this.departmentService = departmentService;
    this.diseaseService = diseaseService;
    this.treatmentService = treatmentService;
    this.patientService = patientService;
    this.visitService = visitService;
    this.visitorService = visitorService;
  }

  public Set<DoctorResponse> getAllDoctors() {
    return doctorService.getAll();
  }

  public void createDoctor(CreationDoctor doctor) {
    doctorService.createDoctor(doctor,
            doctor.getDepartmentId() != null ? getDepartmentById(doctor.getDepartmentId()) : null);
  }

  public void updateDoctor(CreationDoctor doctor, String pesel) {
    doctorService.updateDoctor(doctor, pesel,
            doctor.getDepartmentId() != null ? getDepartmentById(doctor.getDepartmentId()) : null);
  }

  public void deleteDoctor(String pesel) {
    doctorService.deleteDoctor(pesel);
  }

  private Set<Doctor> getDoctorsByPesels(Set<String> pesels) {
    return doctorService.getDoctorsByPesel(pesels);
  }

  public List<MedicalProcedureResponse> getAllMedicalProcedures() {
    return medicalProcedureService.getAllMedicalProcedures();
  }

  public Set<MedicalProcedure> getMedicalProceduresById(Set<Long> medicalProcedureIds) {
    return medicalProcedureService.getMedicalProceduresById(medicalProcedureIds);
  }

  public void createMedicalProcedure(CreationMedicalProcedure medicalProcedure) {
    medicalProcedureService.createMedicalProcedure(medicalProcedure,
            medicalProcedure.getDoctorsIds() != null ? getDoctorsByPesels(medicalProcedure.getDoctorsIds()) : null);
  }

  public void updateMedicalProcedure(CreationMedicalProcedure medicalProcedure, Long medicalProcedureId) {
    medicalProcedureService.updateMedicalProcedure(medicalProcedure,
            medicalProcedure.getDoctorsIds() != null ? getDoctorsByPesels(medicalProcedure.getDoctorsIds()) : null,
            medicalProcedureId);
  }

  public void deleteMedicalProcedure(Long id) {
    medicalProcedureService.deleteMedicalProcedure(id);
  }

  public Set<ClinicResponse> getAllClinics() {
    return clinicService.getAllClinics();
  }

  public void createClinic(CreationClinic clinic) {
    clinicService.createClinic(clinic,
            clinic.getLocalizationId() != null ? getLocalizationById(clinic.getLocalizationId()) : null,
            clinic.getDepartmentIds() != null ?  getAllDepartmentsByIds(clinic.getDepartmentIds()) : null);
  }

  public void deleteClinic(Long clinicId) {
    clinicService.deleteClinic(clinicId);
  }

  public Clinic getClinicById(Long clinicId) {
    return clinicService.getClinicById(clinicId);
  }

  public void updateClinic(CreationClinic clinic, long clinicId) {
    clinicService.updateClinic(clinic,
            clinic.getLocalizationId() != null ? getLocalizationById(clinic.getLocalizationId()) : null,
            clinic.getDepartmentIds() != null ?  getAllDepartmentsByIds(clinic.getDepartmentIds()) : null,
            clinicId);
  }

  public Localization getLocalizationById(Long localizationId) {
    return localizationService.getLocalizationById(localizationId);
  }

  public Set<LocalizationResponse> getAllLocalizations() {
    return localizationService.getAllLocalizations();
  }

  public void createLocalization(CreationLocalization localization) {
    localizationService.createLocalization(localization,
            localization.getClinicId() != null ? getClinicById(localization.getClinicId()) : null);
  }

  public void deleteLocalization(Long localizationId) {
    localizationService.deleteLocalization(localizationId);
  }

  public void updateLocalization(CreationLocalization localization, Long localizationId) {
    localizationService.updateLocalization(localization,
            localization.getClinicId() != null ? getClinicById(localization.getClinicId()) : null,
            localizationId);
  }

  public Set<Department> getAllDepartmentsByIds(Set<Long> ids) {
    return departmentService.getDepartmentsByIds(ids);
  }

  private Department getDepartmentById(Long id) {
    return departmentService.getDepartmentById(id);
  }

  public Set<DepartmentResponse> getAllDepartments() {
    return departmentService.getAllDepartments();
  }

  public void deleteDepartment(Long departmentId) {
    departmentService.deleteDepartment(departmentId);
  }

  public void createDepartment(CreationDepartment department) {
    departmentService.createDepartment(department,
            department.getClinicId() != null ? getClinicById(department.getClinicId()) : null,
            department.getDoctorsPesels() != null ? getDoctorsByPesels(department.getDoctorsPesels()) : null);
  }

  public void updateDepartment(CreationDepartment department, long departmentId) {
    departmentService.updateDepartment(department,
            department.getClinicId() != null ? getClinicById(department.getClinicId()) : null,
            department.getDoctorsPesels() != null ? getDoctorsByPesels(department.getDoctorsPesels()) : null,
            departmentId);
  }

  public Set<DiseaseResponse> getAllDiseases() {
    return diseaseService.getAllDiseases();
  }

  private Disease getDiseaseById(long diseaseId) {
    return diseaseService.getDiseaseById(diseaseId);
  }

  public void createDisease(CreationDisease disease) {
    diseaseService.createDisease(disease,
            disease.getTreatmentsIds() != null ? getTreatmentsByIds(disease.getTreatmentsIds()) : null);
  }

  public void updateDisease(CreationDisease disease, Long diseaseId) {
    diseaseService.updateDisease(disease,
            disease.getTreatmentsIds() != null ? getTreatmentsByIds(disease.getTreatmentsIds()) : null,
            diseaseId);
  }

  public void deleteDisease(Long diseaseId) {
    diseaseService.deleteDisease(diseaseId);
  }

  public Set<TreatmentResponse> getAllTreatments() {
    return treatmentService.getAllTreatments();
  }

  private Set<Treatment> getTreatmentsByIds(Set<Long> treatmentIds) {
    return treatmentService.getTreatmentsByIds(treatmentIds);
  }

  public void createTreatment(CreationTreatment treatment) {
    treatmentService.createTreatment(treatment,
            treatment.getMedicalProceduresIds() != null ? getMedicalProceduresById(treatment.getMedicalProceduresIds()) : null,
            treatment.getPatientPesel() != null ? getPatientByPesel(treatment.getPatientPesel()) : null,
            treatment.getDiseaseId() != null ? getDiseaseById(treatment.getDiseaseId()) : null);
  }

  public void updateTreatment(CreationTreatment treatment, long treatmentId) {
    treatmentService.updateTreatment(treatment,
            treatment.getMedicalProceduresIds() != null ? getMedicalProceduresById(treatment.getMedicalProceduresIds()) : null,
            treatment.getPatientPesel() != null ? getPatientByPesel(treatment.getPatientPesel()) : null,
            treatment.getDiseaseId() != null ? getDiseaseById(treatment.getDiseaseId()) : null,
            treatmentId);
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
    patientService.createPatient(creationPatient,
            creationPatient.getTreatmentIds() != null ? getTreatmentsByIds(creationPatient.getTreatmentIds()) : null,
            creationPatient.getVisitIds() != null ? getVisitsByIds(creationPatient.getVisitIds()) : null);
  }

  public void updatePatient(CreationPatient creationPatient, String pesel) {
    patientService.updatePatient(creationPatient,
            creationPatient.getTreatmentIds() != null ? getTreatmentsByIds(creationPatient.getTreatmentIds()) : null,
            creationPatient.getVisitIds() != null ? getVisitsByIds(creationPatient.getVisitIds()) : null,
            pesel);
  }

  public void deletePatient(String pesel) {
    patientService.deletePatient(pesel);
  }

  private Set<Visit> getVisitsByIds(Set<Long> ids) {
    return visitService.getVisitsById(ids);
  }

  public Set<VisitResponse> getAllVisits(){
    return visitService.getAllVisits();
  }

  public void createVisit(CreationVisit creationVisit) {
    visitService.createVisit(creationVisit,
            creationVisit.getPatientPesel() != null ? getPatientByPesel(creationVisit.getPatientPesel()) : null,
            creationVisit.getVisitorPesel() != null ? getVisitorByPesel(creationVisit.getVisitorPesel()) : null);
  }

  public void updateVisit(CreationVisit creationVisit, Long visitId) {
    visitService.updateVisit(creationVisit,
            creationVisit.getPatientPesel() != null ? getPatientByPesel(creationVisit.getPatientPesel()) : null,
            creationVisit.getVisitorPesel() != null ? getVisitorByPesel(creationVisit.getVisitorPesel()) : null,
            visitId);
  }

  public void deleteVisit(Long visitId) {
    visitService.deleteVisit(visitId);
  }

  public Set<VisitorResponse> getAllVisitors() {
    return visitorService.getAllVisistors();
  }

  private Visitor getVisitorByPesel(String pesel) {
    return visitorService.getVisitorByPesel(pesel);
  }

  public void createVisitor(CreationVisitor visitor) {
    visitorService.createVisitor(visitor,
            visitor.getVisitsIds() != null ? getVisitsByIds(visitor.getVisitsIds()) : null);
  }

  public void updateVisitor(CreationVisitor visitor, String pesel) {
    visitorService.updateVisitor(visitor,
            visitor.getVisitsIds() != null ? getVisitsByIds(visitor.getVisitsIds()) : null,
            pesel);
  }

  public void deleteVisitor(String pesel) {
    visitorService.deleteVisitor(pesel);
  }

  public void raiseSalary(String pesel) {
    doctorService.raiseSalary(pesel);
  }

  public Integer countVisitors(String pesel) {
    return patientService.countVisitors(pesel);
  }
}
