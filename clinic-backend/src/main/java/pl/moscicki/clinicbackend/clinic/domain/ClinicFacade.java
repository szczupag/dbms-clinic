package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationMedicalProcedure;
import pl.moscicki.clinicbackend.clinic.domain.dto.DoctorResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.MedicalProcedureResponse;

import java.util.List;
import java.util.Set;

public class ClinicFacade {

  private DoctorService doctorService;
  private MedicalProcedureService medicalProcedureService;

  public ClinicFacade(DoctorService doctorService, MedicalProcedureService medicalProcedureService) {
    this.doctorService = doctorService;
    this.medicalProcedureService = medicalProcedureService;
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

}
