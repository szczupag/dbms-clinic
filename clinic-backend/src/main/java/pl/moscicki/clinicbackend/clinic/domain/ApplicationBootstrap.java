package pl.moscicki.clinicbackend.clinic.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;

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
  private final DiseaseRepository diseaseRepository;
  private final TreatmentRepository treatmentRepository;

  public ApplicationBootstrap(DoctorRepository doctorRepository, MedicalProcedureRepository medicalProcedureRepository,
                              ClinicRepository clinicRepository, LocalizationRepository localizationRepository,
                              DepartmentRepository departmentRepository, PatientRepository patientRepository,
                              VisitorRepository visitorRepository, VisitRepository visitRepository,
                              DiseaseRepository diseaseRepository, TreatmentRepository treatmentRepository) {
    this.doctorRepository = doctorRepository;
    this.medicalProcedureRepository = medicalProcedureRepository;
    this.clinicRepository = clinicRepository;
    this.localizationRepository = localizationRepository;
    this.departmentRepository = departmentRepository;
    this.patientRepository = patientRepository;
    this.visitorRepository = visitorRepository;
    this.visitRepository = visitRepository;
    this.treatmentRepository = treatmentRepository;
    this.diseaseRepository = diseaseRepository;
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
    MedicalProcedure przeszczepWatroby =  MedicalProcedure.builder()
            .name("Przeszczep wątroby")
            .cost(10000L)
            .date(new Date(1547912554813L))
            .doctors(new HashSet<>(Arrays.asList(maciej, agata)))
            .build();

    medicalProcedureRepository.save(przeszczepWatroby);
    maciej.setMedicalProcedures(new HashSet<>(Collections.singletonList(przeszczepWatroby)));
    agata.setMedicalProcedures(new HashSet<>(Collections.singletonList(przeszczepWatroby)));
    doctorRepository.save(maciej);
    doctorRepository.save(agata);

    //Diseases
    Disease katar = Disease.builder()
            .name("katar")
            .severity("niska")
            .build();

    diseaseRepository.save(katar);

    //Patients
    Patient patient = Patient.builder()
            .pesel("12345678914")
            .firstName("Jan")
            .lastName("Kowalski")
            .phoneNumber("666-666-666")
            .build();

    patientRepository.save(patient);

    //Treatments
    Treatment treatment = Treatment.builder()
            .startDate(new Date(1547912551813L))
            .endDate(new Date(1547912554813L))
            .build();

    treatmentRepository.save(treatment);

    treatment.setDisease(katar);
    treatment.setPatient(patient);
    treatment.setMedicalProcedures(new HashSet<>(Collections.singletonList(przeszczepWatroby)));
    katar.setTreatments(new HashSet<>(Collections.singletonList(treatment)));
    patient.setTreatments(new HashSet<>(Collections.singletonList(treatment)));
    przeszczepWatroby.setTreatment(treatment);

    treatmentRepository.save(treatment);

    //Visitors

    Visitor visitor = Visitor.builder()
            .firstName("Mariusz")
            .lastName("Tomaszyk")
            .pesel("12345678909")
            .idNumber("ADZ-233")
            .build();

    visitorRepository.save(visitor);

    Visit visit = Visit.builder()
            .visitDate(new Date(1547212554813L))
            .build();

    visitRepository.save(visit);

    visit.setPatient(patient);
    visit.setVisitor(visitor);

    visitRepository.save(visit);
    log.info("Added entities to DB on application startup");
  }
}
