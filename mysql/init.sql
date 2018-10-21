SET GLOBAL default_storage_engine = 'InnoDB';

/* 
CREATE TABLE `sys`.`doctors` (
  `pesel` varchar(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `salary` bigint(20),
  `speciality` varchar(255),
  `supervisor_pesel` varchar(255),
  PRIMARY KEY (`pesel`)
); 
 
CREATE TABLE `sys`.`medical_procedures` (
  `medical_procedure_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cost` bigint(20),
  PRIMARY KEY (`medical_procedure_id`)
); 

CREATE TABLE `sys`.`treatments` (
  `treatment_id` bigint(20) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date,
  PRIMARY KEY (`treatment_id`)
); 

CREATE TABLE `sys`.`patients` (
  `pesel` varchar(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone_number` varchar(15),
  PRIMARY KEY (`pesel`)
); 

CREATE TABLE `sys`.`visitors` (
  `pesel` varchar(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `id_number` varchar(7) NOT NULL,
  PRIMARY KEY (`pesel`)
); 

CREATE TABLE `sys`.`medicines` (
  `medicine_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `recommended_dose` bigint(20) NOT NULL,
  `cost` bigint(20) NOT NULL,
  PRIMARY KEY (`medicine_id`)
); 

CREATE TABLE `sys`.`diseases` (
  `disease_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `severity`  varchar(255),
  PRIMARY KEY (`disease_id`)
); 
 
CREATE TABLE `sys`.`doctors_medical_procedures` (
  `doctors_medical_procedures_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pesel` varchar(20) NOT NULL,
  `medical_procedure_id` bigint(20) NOT NULL,
  PRIMARY KEY (`doctors_medical_procedures_id`),
  KEY `fk_doctor` (`pesel`),
  KEY `fk_medical_procedure` (`medical_procedure_id`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`pesel`) REFERENCES `doctors` (`pesel`),
  CONSTRAINT `fk_medical_procedure` FOREIGN KEY (`medical_procedure_id`) REFERENCES `medical_procedures` (`medical_procedure_id`)
);

CREATE TABLE `sys`.`treatments_mecial_procedures` (
  `treatments_mecial_procedures_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `treatment_id` bigint(20) NOT NULL,
  `medical_procedure_id` bigint(20) NOT NULL,
  PRIMARY KEY (`treatments_mecial_procedures_id`),
  KEY `fk_treatment_id` (`treatment_id`),
  KEY `fk_medical_procedure_2` (`medical_procedure_id`),
  CONSTRAINT `fk_treatment_id` FOREIGN KEY (`treatment_id`) REFERENCES `treatments` (`treatment_id`),
  CONSTRAINT `fk_medical_procedure_2` FOREIGN KEY (`medical_procedure_id`) REFERENCES `medical_procedures` (`medical_procedure_id`)
);

CREATE TABLE `sys`.`patients_visitors` (
  `patients_visitors_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_pesel` varchar(20) NOT NULL,
  `visitor_pesel` varchar(20) NOT NULL,
  PRIMARY KEY (`patients_visitors_id`),
  KEY `fk_patient_pesel` (`patient_pesel`),
  KEY `fk_visitor_pesel` (`visitor_pesel`),
  CONSTRAINT `fk_patient_pesel` FOREIGN KEY (`patient_pesel`) REFERENCES `patients` (`pesel`),
  CONSTRAINT `fk_visitor_pesel` FOREIGN KEY (`visitor_pesel`) REFERENCES `visitors` (`pesel`)
); */
