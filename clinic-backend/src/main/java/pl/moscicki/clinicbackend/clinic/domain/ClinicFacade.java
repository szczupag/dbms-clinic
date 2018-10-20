package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.clinic.domain.dto.CreationTreatment;
import pl.moscicki.clinicbackend.clinic.domain.dto.DoctorResponse;
import pl.moscicki.clinicbackend.clinic.domain.dto.TreatmentResponse;

import java.util.List;
import java.util.Set;

public class ClinicFacade {

  private DoctorService doctorService;
  private TreatmentService treatmentService;

  public ClinicFacade(DoctorService doctorService, TreatmentService treatmentService) {
    this.doctorService = doctorService;
    this.treatmentService = treatmentService;
  }

  public Set<DoctorResponse> getAllDoctors(boolean withTreatments) {
    return doctorService.getAll(withTreatments);
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

  public List<TreatmentResponse> getAllTreatments(boolean withDoctors) {
    return treatmentService.getAllTreatments(withDoctors);
  }

  public void createTreatment(CreationTreatment treatment) {
    treatmentService.createTreatment(treatment, getDoctorsByPesels(treatment.getDoctorsIds()));
  }

  public void deleteTreatment(Long id) {
    treatmentService.deleteTreatment(id);
  }

}
