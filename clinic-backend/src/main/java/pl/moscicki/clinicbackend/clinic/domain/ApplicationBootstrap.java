package pl.moscicki.clinicbackend.clinic.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Component
@Slf4j
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;
  private final MedicalProcedureRepository medicalProcedureRepository;
  private final ClinicRepository clinicRepository;
  private final LocalizationRepository localizationRepository;
  private final DepartmentRepository departmentRepository;
  private final VisitorRepository visitorRepository;
  private final VisitRepository visitRepository;

  public ApplicationBootstrap(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository,
                              ClinicRepository clinicRepository, LocalizationRepository localizationRepository,
                              DepartmentRepository departmentRepository, PatientRepository patientRepository,
                              VisitorRepository visitorRepository, VisitRepository visitRepository) {
    this.doctorRepository = doctorRepository;
    this.medicalProcedureRepository = medicalProcedureRepository;
    this.clinicRepository = clinicRepository;
    this.localizationRepository = localizationRepository;
    this.departmentRepository = departmentRepository;
    this.patientRepository = patientRepository;
    this.visitorRepository = visitorRepository;
    this.visitRepository = visitRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    log.info("Adding entities to DB on application startup");
    Doctor maciej = Doctor.builder()
            .pesel("12345678902")
            .firstName("Maciej")
            .lastName("Moscicki")
            .salary(10000L)
            .speciality("Chirurg")
            .medicalProcedures(new HashSet<>())
            .build();

    Doctor agata = Doctor.builder()
            .pesel("12345678901")
            .firstName("Agata")
            .lastName("Szczuka")
            .speciality("Kardiolog")
            .supervisor(maciej)
            .medicalProcedures(new HashSet<>())
            .build();

    Patient patient = Patient.builder()
            .firstName("pacjent")
            .lastName("kowalski")
            .pesel("12345678903")
            .build();

    patientRepository.save(patient);

    Visitor visitor = Visitor.builder()
            .firstName("wizytujacy")
            .lastName("nowak")
            .pesel("12345678904")
            .idNumber("123")
            .build();

    Visitor visitor2 = Visitor.builder()
            .firstName("wizytujacy2")
            .lastName("nowak")
            .pesel("12345678905")
            .idNumber("123")
            .build();

    visitorRepository.save(visitor);
    visitorRepository.save(visitor2);

    Visit visit = Visit.builder()
            .visitDate(new Date(1547305232))
            .patient(patient)
            .visitor(visitor)
            .build();

    Visit visit2 = Visit.builder()
            .visitDate(new Date(1547305231))
            .patient(patient)
            .visitor(visitor2)
            .build();

    visitRepository.save(visit);
    visitRepository.save(visit2);

    doctorRepository.dropProcedureRaiseIfExists();
    doctorRepository.createProcedureRaise();
    patientRepository.dropCreaterVisitorsCountFunctionIfExists();
    patientRepository.createVisitorsCountFunction();

    doctorRepository.raise("12345678902");

//    departmentRepository.save(department);
//
//    maciej.setDepartment(department);
//    agata.setDepartment(department);
//
//    localizationRepository.save(localization);
//
//    clinicRepository.save(clinic);
//
//    localization.setClinic(clinic);
//
//    localizationRepository.save(localization);
//
//    doctorRepository.save(maciej);
//    doctorRepository.save(agata);
//
//    department.setDoctors(new HashSet<>(Arrays.asList(maciej, agata)));
//
//    departmentRepository.save(department);
//
//    clinic.setDepartments(new HashSet<>(Arrays.asList(department)));
//
//    clinicRepository.save(clinic);
//
//    department.setClinic(clinic);
//    departmentRepository.save(department);
//
//    medicalProcedureRepository.save(masaz_serca);
//
//    maciej.getMedicalProcedures().add(masaz_serca);
//    agata.getMedicalProcedures().add(masaz_serca);

    doctorRepository.save(maciej);
    doctorRepository.save(agata);



    log.info("Added entities to DB on application startup");
  }
}
