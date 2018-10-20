package pl.moscicki.clinicbackend.doctor.domain;

import pl.moscicki.clinicbackend.doctor.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.doctor.domain.dto.DoctorResponse;

import java.util.List;

public class DoctorFacade {

  private DoctorService doctorService;

  public DoctorFacade(DoctorService doctorService) {
    this.doctorService = doctorService;
  }

  public List<DoctorResponse> getAllDoctors() {
    return doctorService.getAll();
  }

  public void createDoctor(CreationDoctor doctor) {
    doctorService.createDoctor(doctor);
  }
}
