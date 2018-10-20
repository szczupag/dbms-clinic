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
  private final TreatmentRepository treatmentRepository;

  public ApplicationBootstrap(DoctorRepository doctorRepository, TreatmentRepository treatmentRepository) {
    this.doctorRepository = doctorRepository;
    this.treatmentRepository = treatmentRepository;
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
            .treatments(new HashSet<>())
            .build();

    Doctor agata = Doctor.builder()
            .pesel("12345678901")
            .firstName("Agata")
            .lastName("Szczuka")
            .speciality("Kardiolog")
            .supervisor(maciej)
            .treatments(new HashSet<>())
            .build();

    Treatment masaz_serca = Treatment.builder()
            .name("Masaz serca")
            .cost(650L)
            .doctors(new HashSet<>(Arrays.asList(maciej, agata)))
            .build();

    doctorRepository.save(maciej);
    doctorRepository.save(agata);

    treatmentRepository.save(masaz_serca);

    maciej.getTreatments().add(masaz_serca);
    agata.getTreatments().add(masaz_serca);

    doctorRepository.save(maciej);
    doctorRepository.save(agata);



    log.info("Added entities to DB on application startup");
  }
}
