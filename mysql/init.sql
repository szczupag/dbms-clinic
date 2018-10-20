
CREATE TABLE `sys`.`doctors` (
  `pesel` varchar(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `salary` bigint(20),
  `speciality` varchar(255),
  `supervisor_pesel` varchar(255),
  PRIMARY KEY (`pesel`)
); 
 
CREATE TABLE `sys`.`treatments` (
  `treatment_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cost` bigint(20),
  PRIMARY KEY (`treatment_id`)
); 
 
CREATE TABLE `sys`.`doctors_treatments` (
  `doctors_treatments_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pesel` varchar(20) NOT NULL,
  `treatment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`doctors_treatments_id`),
  KEY `fk_doctor` (`pesel`),
  KEY `fk_treatment_id` (`treatment_id`),
  CONSTRAINT `fk_doctor` FOREIGN KEY (`pesel`) REFERENCES `doctors` (`pesel`),
  CONSTRAINT `fk_treatment_id` FOREIGN KEY (`treatment_id`) REFERENCES `treatments` (`treatment_id`)
);