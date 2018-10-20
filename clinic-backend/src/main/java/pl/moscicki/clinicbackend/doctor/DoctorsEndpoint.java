package pl.moscicki.clinicbackend.doctor;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.moscicki.clinicbackend.doctor.domain.DoctorFacade;
import pl.moscicki.clinicbackend.doctor.domain.dto.CreationDoctor;
import pl.moscicki.clinicbackend.doctor.domain.dto.DoctorResponse;

import java.util.List;

@RestController
@RequestMapping("/doctors")
class DoctorsEndpoint {

  private DoctorFacade doctorFacade;

  DoctorsEndpoint(DoctorFacade doctorFacade) {
    this.doctorFacade = doctorFacade;
  }

  @GetMapping("/all")
  List<DoctorResponse> getAll() {
    return doctorFacade.getAllDoctors();
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.OK)
  void createDoctor(@RequestBody @Validated CreationDoctor doctor) {
    doctorFacade.createDoctor(doctor);
  }

}
