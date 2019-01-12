package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

interface PatientRepository extends CrudRepository<Patient, String> {

  @Override
  Set<Patient> findAll();

  @Transactional
  @Modifying
  @Query(value =
          "CREATE FUNCTION `sys`.`visitors_count` (patient_psl varchar(13))\n" +
          "\tRETURNS int \n" +
          "    DETERMINISTIC\n" +
          "    READS SQL DATA\n" +
          "    CONTAINS SQL\n" +
          "BEGIN\n" +
          "\tDECLARE visitors_count int;\n" +
          "    SELECT COUNT(*) INTO visitors_count FROM visits where patient_pesel = patient_psl;\n" +
          "\tRETURN visitors_count;\n" +
          "END ", nativeQuery = true)
  void createVisitorsCountFunction();

  @Transactional
  @Modifying
  @Query(value =
          "DROP FUNCTION IF EXISTS `sys`.`visitors_count`", nativeQuery = true)
  void dropCreaterVisitorsCountFunctionIfExists();

}
