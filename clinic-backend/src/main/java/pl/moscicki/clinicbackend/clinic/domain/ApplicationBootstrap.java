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

  public ApplicationBootstrap(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository) {
    this.doctorRepository = doctorRepository;
    this.medicalProcedureRepository = medicalProcedureRepository;
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

    doctorRepository.save(maciej);
    doctorRepository.save(agata);

    medicalProcedureRepository.save(masaz_serca);

    maciej.getMedicalProcedures().add(masaz_serca);
    agata.getMedicalProcedures().add(masaz_serca);

    doctorRepository.save(maciej);
    doctorRepository.save(agata);



    log.info("Added entities to DB on application startup");
  }
}
