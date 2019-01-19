package pl.moscicki.clinicbackend.clinic.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    //Localizations
    Localization szpitalna = Localization.builder()
            .city("Poznan")
            .street("Szpitalna")
            .buildingNo(13L)
            .postalCode("62-002")
            .clinic(null)
            .build();

    localizationRepository.save(szpitalna);

    //Departments
    Department departmentOnkologii = Department.builder()
            .name("Oddział onkologii")
            .build();

    Department departmentKardiologii = Department.builder()
            .name("Oddział kardiologii")
            .build();

    departmentRepository.save(departmentKardiologii);
    departmentRepository.save(departmentOnkologii);

    //Kliniki
    Localization lokalizacjaSzpitalna = localizationRepository.findById(1L).orElse(null);
    Set<Department> departmentySzpitalna = departmentRepository.findAllByDepartmentIdIn(new HashSet<>(Arrays.asList(2L,3L)));

    Clinic clinic = Clinic.builder()
            .name("Szpital MSW")
            .type("Ogolny")
            .build();

    clinicRepository.save(clinic);

    clinic.setLocalization(lokalizacjaSzpitalna);
    clinic.setDepartments(departmentySzpitalna);
    lokalizacjaSzpitalna.setClinic(clinic);
    departmentySzpitalna.forEach(department -> department.setClinic(clinic));

    clinicRepository.save(clinic);

    //Doctors
    Doctor maciej = Doctor.builder()
            .pesel("12345678902")
            .firstName("Maciej")
            .lastName("Moscicki")
            .salary(10000L)
            .speciality("Chirurg")
            .department(departmentOnkologii)
            .medicalProcedures(new HashSet<>())
            .build();

    Doctor agata = Doctor.builder()
            .pesel("12345678901")
            .firstName("Agata")
            .lastName("Szczuka")
            .speciality("Kardiolog")
            .department(departmentOnkologii)
            .supervisor(maciej)
            .medicalProcedures(new HashSet<>())
            .build();

    doctorRepository.save(maciej);
    doctorRepository.save(agata);

    //Medical procedures
//    MedicalProcedure przeszczepWatroby =  MedicalProcedure.builder()
//            .name("Przeszczep wątroby")
//            .cost(10000L)
//            .doctors(new HashSet<>(Arrays.asList(maciej, agata)))
//            .build();
//
//    medicalProcedureRepository.save(przeszczepWatroby);
//    maciej.setMedicalProcedures(new HashSet<>(Arrays.asList(przeszczepWatroby)));
//    agata.setMedicalProcedures(new HashSet<>(Arrays.asList(przeszczepWatroby)));
//    doctorRepository.save(maciej);
//    doctorRepository.save(agata);



    log.info("Added entities to DB on application startup");
  }
}
