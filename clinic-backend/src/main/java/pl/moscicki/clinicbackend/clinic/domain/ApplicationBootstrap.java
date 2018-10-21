package pl.moscicki.clinicbackend.clinic.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
@Slf4j
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final DoctorRepository doctorRepository;
  private final MedicalProcedureRepository medicalProcedureRepository;
  private final ClinicRepository clinicRepository;
  private final LocalizationRepository localizationRepository;
  private final DepartmentRepository departmentRepository;

  public ApplicationBootstrap(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository,
                              ClinicRepository clinicRepository, LocalizationRepository localizationRepository,
                              DepartmentRepository departmentRepository) {
    this.doctorRepository = doctorRepository;
    this.medicalProcedureRepository = medicalProcedureRepository;
    this.clinicRepository = clinicRepository;
    this.localizationRepository = localizationRepository;
    this.departmentRepository = departmentRepository;
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

    MedicalProcedure masaz_serca = MedicalProcedure.builder()
            .name("Masaz serca")
            .cost(650L)
            .doctors(new HashSet<>(Arrays.asList(maciej, agata)))
            .build();

    Localization localization = Localization.builder()
            .city("Poznan")
            .postalCode("60-023")
            .buildingNo(7L)
            .street("Szpitalna")
            .build();

    Clinic clinic = Clinic.builder()
            .type("Klinika chirurgi plastycznej")
            .name("Piekna skora")
            .localization(localization)
            .build();

    Department department = Department.builder()
            .name("Dermatologia")
            .build();

    departmentRepository.save(department);

    maciej.setDepartment(department);
    agata.setDepartment(department);

    localizationRepository.save(localization);

    clinicRepository.save(clinic);

    localization.setClinic(clinic);

    localizationRepository.save(localization);

    doctorRepository.save(maciej);
    doctorRepository.save(agata);

    department.setDoctors(new HashSet<>(Arrays.asList(maciej, agata)));

    departmentRepository.save(department);

    clinic.setDepartments(new HashSet<>(Arrays.asList(department)));

    clinicRepository.save(clinic);

    department.setClinic(clinic);
    departmentRepository.save(department);

    medicalProcedureRepository.save(masaz_serca);

    maciej.getMedicalProcedures().add(masaz_serca);
    agata.getMedicalProcedures().add(masaz_serca);

    doctorRepository.save(maciej);
    doctorRepository.save(agata);



    log.info("Added entities to DB on application startup");
  }
}
