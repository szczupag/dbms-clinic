package pl.moscicki.clinicbackend.doctors.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final DoctorRepository doctorRepository;

  public ApplicationBootstrap(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    log.info("Adding doctors to DB on application startup");
    Doctor maciej = new Doctor("12345678902", "Maciej", "Moscicki", 1000L, "Chirurg", null);
    Doctor agata = new Doctor("12345678901", "Agata", "Szczuka", 0L, "Sprzataczka" , maciej);
    doctorRepository.save(maciej);
    doctorRepository.save(agata);
    log.info("Added doctors to DB on application startup");
  }
}
