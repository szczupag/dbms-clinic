package pl.moscicki.clinicbackend.clinic.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

interface DoctorRepository extends CrudRepository<Doctor, String> {

  @Override
  Set<Doctor> findAll();

  Set<Doctor> findAllByPeselIn(Set<String> doctorsPesels);

  @Transactional
  @Modifying
  @Query(value =
          "CREATE PROCEDURE `sys`.`raise` (IN doctor_pesel varchar(13)) \n" +
          "BEGIN\n" +
          "\tupdate doctors set salary = salary * 1.1 where pesel =  doctor_pesel; \n" +
          "END", nativeQuery = true)
  void createProcedureRaise();

  @Transactional
  @Modifying
  @Query(value =
          "DROP PROCEDURE IF EXISTS `sys`.`raise`", nativeQuery = true)
  void dropProcedureRaiseIfExists();



}
