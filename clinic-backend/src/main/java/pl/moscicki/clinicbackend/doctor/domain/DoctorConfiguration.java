package pl.moscicki.clinicbackend.doctor.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DoctorConfiguration {

  @Bean
  DoctorFacade doctorsFacade(DoctorRepository doctorRepository) {
    DoctorService doctorService = new DoctorService(doctorRepository);

    return new DoctorFacade(doctorService);
  }
}
