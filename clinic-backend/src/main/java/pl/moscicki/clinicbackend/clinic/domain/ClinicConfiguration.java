package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClinicConfiguration {

  @Bean
  ClinicFacade doctorsFacade(DoctorRepository doctorRepository, TreatmentRepository treatmentRepository) {
    DoctorService doctorService = new DoctorService(doctorRepository);
    TreatmentService treatmentService = new TreatmentService(treatmentRepository);

    return new ClinicFacade(doctorService, treatmentService);
  }
}
