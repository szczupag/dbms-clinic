package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClinicConfiguration {

  @Bean
  ClinicFacade clinicFacade(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository,
                            ClinicRepository clinicRepository, LocalizationRepository localizationRepository,
                            DepartmentRepository departmentRepository) {

    DoctorService doctorService = new DoctorService(doctorRepository);
    MedicalProcedureService medicalProcedureService = new MedicalProcedureService(medicalProcedureRepository);
    ClinicService clinicService = new ClinicService(clinicRepository);
    LocalizationService localizationService = new LocalizationService(localizationRepository);
    DepartmentService departmentService = new DepartmentService(departmentRepository);

    return new ClinicFacade(doctorService, medicalProcedureService, clinicService, localizationService, departmentService);
  }
}
