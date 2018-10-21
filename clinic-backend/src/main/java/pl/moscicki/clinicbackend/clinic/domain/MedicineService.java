package pl.moscicki.clinicbackend.clinic.domain;

class MedicineService {
  private MedicineRepository medicineRepository;

  public MedicineService(MedicineRepository medicineRepository) {
    this.medicineRepository = medicineRepository;
  }
}
