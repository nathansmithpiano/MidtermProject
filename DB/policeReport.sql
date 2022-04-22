-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema eleidb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `eleidb` ;

-- -----------------------------------------------------
-- Schema eleidb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `eleidb` DEFAULT CHARACTER SET utf8 ;
USE `eleidb` ;

-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

CREATE TABLE IF NOT EXISTS `person` (
  `id` INT NOT NULL COMMENT 'User isA Person\\n',
  `type` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `age` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `ethnicity` VARCHAR(45) NOT NULL,
  `address_id` VARCHAR(45) NULL DEFAULT NULL,
  `supervisor_id` INT NULL,
  `officer_id` INT NULL,
  `flag` TINYINT NULL,
  `description` TEXT NULL,
  `notes` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `officer_id_UNIQUE` (`officer_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL,
  `person_id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `active` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_User_Person1_idx` (`person_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_User_Person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `department` ;

CREATE TABLE IF NOT EXISTS `department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` VARCHAR(45) NULL,
  `person_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `street1` VARCHAR(255) NULL DEFAULT NULL,
  `street2` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state_code` VARCHAR(2) NULL DEFAULT NULL,
  `zip` INT(5) UNSIGNED NULL DEFAULT NULL,
  `department_id` INT NOT NULL,
  `flag` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_address_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_has_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_has_address` ;

CREATE TABLE IF NOT EXISTS `person_has_address` (
  `person_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`person_id`, `address_id`),
  INDEX `fk_Person_has_Address_Address1_idx` (`address_id` ASC),
  INDEX `fk_Person_has_Address_Person_idx` (`person_id` ASC),
  CONSTRAINT `fk_Person_has_Address_Person`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Address_Address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case` ;

CREATE TABLE IF NOT EXISTS `case` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `flag` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident` ;

CREATE TABLE IF NOT EXISTS `incident` (
  `id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  `address_id` INT NOT NULL,
  `case_id` INT NOT NULL,
  `description` TEXT NULL,
  `flag` TINYINT NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_incident_address1_idx` (`address_id` ASC),
  INDEX `fk_incident_case1_idx` (`case_id` ASC),
  CONSTRAINT `fk_incident_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_case1`
    FOREIGN KEY (`case_id`)
    REFERENCES `case` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_has_incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_has_incident` ;

CREATE TABLE IF NOT EXISTS `person_has_incident` (
  `person_id` INT NOT NULL,
  `incident_id` INT NOT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`person_id`, `incident_id`),
  INDEX `fk_person_has_incident_incident1_idx` (`incident_id` ASC),
  INDEX `fk_person_has_incident_person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_person_has_incident_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_incident_incident1`
    FOREIGN KEY (`incident_id`)
    REFERENCES `incident` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `department_has_person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `department_has_person` ;

CREATE TABLE IF NOT EXISTS `department_has_person` (
  `department_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`department_id`, `person_id`),
  INDEX `fk_department_has_person_person1_idx` (`person_id` ASC),
  INDEX `fk_department_has_person_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_department_has_person_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_department_has_person_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` TIMESTAMP NULL,
  `updated` TIMESTAMP NULL,
  `text` TEXT NULL,
  `user_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident_has_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident_has_note` ;

CREATE TABLE IF NOT EXISTS `incident_has_note` (
  `incident_id` INT NOT NULL,
  `note_id` INT NOT NULL,
  PRIMARY KEY (`incident_id`, `note_id`),
  INDEX `fk_incident_has_note_note1_idx` (`note_id` ASC),
  INDEX `fk_incident_has_note_incident1_idx` (`incident_id` ASC),
  CONSTRAINT `fk_incident_has_note_incident1`
    FOREIGN KEY (`incident_id`)
    REFERENCES `incident` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_has_note_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `case_has_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_has_note` ;

CREATE TABLE IF NOT EXISTS `case_has_note` (
  `case_id` INT NOT NULL,
  `note_id` INT NOT NULL,
  PRIMARY KEY (`case_id`, `note_id`),
  INDEX `fk_case_has_note_note1_idx` (`note_id` ASC),
  INDEX `fk_case_has_note_case1_idx` (`case_id` ASC),
  CONSTRAINT `fk_case_has_note_case1`
    FOREIGN KEY (`case_id`)
    REFERENCES `case` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_has_note_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_has_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_has_note` ;

CREATE TABLE IF NOT EXISTS `person_has_note` (
  `person_id` INT NOT NULL,
  `note_id` INT NOT NULL,
  PRIMARY KEY (`person_id`, `note_id`),
  INDEX `fk_person_has_note_note1_idx` (`note_id` ASC),
  INDEX `fk_person_has_note_person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_person_has_note_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_note_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS officeruser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'officeruser'@'localhost' IDENTIFIED BY 'officeruser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'officeruser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `person`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `person` (`id`, `type`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `age`, `gender`, `ethnicity`, `address_id`, `supervisor_id`, `officer_id`, `flag`, `description`, `notes`) VALUES (1, '1', 'test', 'test', 'test', 'admin', NULL, '22', 'male', 'white', NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `user` (`id`, `person_id`, `type`, `username`, `password`, `active`, `role`) VALUES (1, 1, 'admin', 'admin', 'admin', 1, 'role_admin');

COMMIT;

