package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClinicConfiguration {

  @Bean
  ClinicFacade doctorsFacade(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository) {
    DoctorService doctorService = new DoctorService(doctorRepository);
    MedicalProcedureService medicalProcedureService = new MedicalProcedureService(medicalProcedureRepository);

    return new ClinicFacade(doctorService, medicalProcedureService);
  }
}
