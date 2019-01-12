package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClinicConfiguration {

  @Bean
  ClinicFacade clinicFacade(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository,
                            ClinicRepository clinicRepository, LocalizationRepository localizationRepository,
                            DepartmentRepository departmentRepository, DiseaseRepository diseaseRepository,
                            TreatmentRepository treatmentRepository, PatientRepository patientRepository,
                            VisitRepository visitRepository, VisitorRepository visitorRepository) {

    DoctorService doctorService = new DoctorService(doctorRepository);
    MedicalProcedureService medicalProcedureService = new MedicalProcedureService(medicalProcedureRepository);
    ClinicService clinicService = new ClinicService(clinicRepository);
    LocalizationService localizationService = new LocalizationService(localizationRepository);
    DepartmentService departmentService = new DepartmentService(departmentRepository);
    DiseaseService diseaseService = new DiseaseService(diseaseRepository);
    TreatmentService treatmentService = new TreatmentService(treatmentRepository);
    PatientService patientService = new PatientService(patientRepository);
    VisitService visitService = new VisitService(visitRepository);
    VisitorService visitorService = new VisitorService(visitorRepository);

    return new ClinicFacade(doctorService, medicalProcedureService, clinicService, localizationService,
            departmentService, diseaseService, treatmentService, patientService, visitService, visitorService);
  }
}
