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
-- Table `ethnicity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ethnicity` ;

CREATE TABLE IF NOT EXISTS `ethnicity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

CREATE TABLE IF NOT EXISTS `person` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'User isA Person\\n',
  `record_id` INT NULL,
  `ethnicity_id` INT NOT NULL DEFAULT 7,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `gender` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_person_ethnicity1_idx` (`ethnicity_id` ASC),
  INDEX `fk_person_person1_idx` (`record_id` ASC),
  CONSTRAINT `fk_person_ethnicity1`
    FOREIGN KEY (`ethnicity_id`)
    REFERENCES `ethnicity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_person1`
    FOREIGN KEY (`record_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `permission_level` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT 0,
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
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `record_id` INT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `street1` VARCHAR(255) NULL DEFAULT NULL,
  `street2` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state_code` VARCHAR(2) NULL DEFAULT NULL,
  `zip` INT(5) UNSIGNED NULL DEFAULT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_address_address1_idx` (`record_id` ASC),
  CONSTRAINT `fk_address_address1`
    FOREIGN KEY (`record_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_address` ;

CREATE TABLE IF NOT EXISTS `person_address` (
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
-- Table `case_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_file` ;

CREATE TABLE IF NOT EXISTS `case_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `record_id` INT NULL,
  `suspected_crime` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_case_file_case_file1_idx` (`record_id` ASC),
  CONSTRAINT `fk_case_file_case_file1`
    FOREIGN KEY (`record_id`)
    REFERENCES `case_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident` ;

CREATE TABLE IF NOT EXISTS `incident` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `record_id` INT NULL,
  `address_id` INT NOT NULL,
  `case_id` INT NULL,
  `reason_for_contact` VARCHAR(45) NULL DEFAULT NULL,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  `incident_date` DATETIME NULL,
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_incident_address1_idx` (`address_id` ASC),
  INDEX `fk_incident_case1_idx` (`case_id` ASC),
  INDEX `fk_incident_incident1_idx` (`record_id` ASC),
  CONSTRAINT `fk_incident_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_case1`
    FOREIGN KEY (`case_id`)
    REFERENCES `case_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_incident1`
    FOREIGN KEY (`record_id`)
    REFERENCES `incident` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident_with_person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident_with_person` ;

CREATE TABLE IF NOT EXISTS `incident_with_person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `incident_id` INT NOT NULL,
  `record_id_person_id` INT NULL,
  `record_id_incident_id` INT NULL,
  `suspected_crime` VARCHAR(45) NULL,
  `age_minimum` INT NOT NULL,
  `age_maximum` INT NOT NULL,
  `description` TEXT NULL,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`person_id`, `incident_id`),
  INDEX `fk_person_has_incident_incident1_idx` (`incident_id` ASC),
  INDEX `fk_person_has_incident_person1_idx` (`person_id` ASC),
  INDEX `fk_incident_with_person_incident_with_person1_idx` (`record_id_person_id` ASC, `record_id_incident_id` ASC),
  CONSTRAINT `fk_person_has_incident_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_incident_incident1`
    FOREIGN KEY (`incident_id`)
    REFERENCES `incident` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_with_person_incident_with_person1`
    FOREIGN KEY (`record_id_person_id` , `record_id_incident_id`)
    REFERENCES `incident_with_person` (`person_id` , `incident_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `department` ;

CREATE TABLE IF NOT EXISTS `department` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_department_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_department_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `record_id` INT NULL,
  `user_id` INT NOT NULL,
  `created` TIMESTAMP NULL,
  `updated` TIMESTAMP NULL,
  `content` TEXT NULL,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_note_note1_idx` (`record_id` ASC),
  CONSTRAINT `fk_note_note1`
    FOREIGN KEY (`record_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident_note` ;

CREATE TABLE IF NOT EXISTS `incident_note` (
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
-- Table `case_file_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `case_file_note` ;

CREATE TABLE IF NOT EXISTS `case_file_note` (
  `case_id` INT NOT NULL,
  `note_id` INT NOT NULL,
  PRIMARY KEY (`case_id`, `note_id`),
  INDEX `fk_case_has_note_note1_idx` (`note_id` ASC),
  INDEX `fk_case_has_note_case1_idx` (`case_id` ASC),
  CONSTRAINT `fk_case_has_note_case1`
    FOREIGN KEY (`case_id`)
    REFERENCES `case_file` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_case_has_note_note1`
    FOREIGN KEY (`note_id`)
    REFERENCES `note` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person_note` ;

CREATE TABLE IF NOT EXISTS `person_note` (
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


-- -----------------------------------------------------
-- Table `officer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `officer` ;

CREATE TABLE IF NOT EXISTS `officer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `record_id` INT NULL,
  `person_id` INT NOT NULL,
  `supervisor_id` INT NULL,
  `badge` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  `archived` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_officer_officer1_idx` (`supervisor_id` ASC),
  UNIQUE INDEX `badge_UNIQUE` (`badge` ASC),
  INDEX `fk_officer_officer2_idx` (`record_id` ASC),
  CONSTRAINT `fk_officer_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_officer_officer1`
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `officer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_officer_officer2`
    FOREIGN KEY (`record_id`)
    REFERENCES `officer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `department_employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `department_employee` ;

CREATE TABLE IF NOT EXISTS `department_employee` (
  `officer_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  PRIMARY KEY (`officer_id`, `department_id`),
  INDEX `fk_officer_has_department_department1_idx` (`department_id` ASC),
  INDEX `fk_officer_has_department_officer1_idx` (`officer_id` ASC),
  CONSTRAINT `fk_officer_has_department_officer1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `officer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_officer_has_department_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident_with_person_has_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident_with_person_has_note` ;

CREATE TABLE IF NOT EXISTS `incident_with_person_has_note` (
  `incident_with_person_person_id` INT NOT NULL,
  `incident_with_person_incident_id` INT NOT NULL,
  `note_id` INT NOT NULL,
  PRIMARY KEY (`incident_with_person_person_id`, `incident_with_person_incident_id`, `note_id`),
  INDEX `fk_incident_with_person_has_note_note1_idx` (`note_id` ASC),
  INDEX `fk_incident_with_person_has_note_incident_with_person1_idx` (`incident_with_person_person_id` ASC, `incident_with_person_incident_id` ASC),
  CONSTRAINT `fk_incident_with_person_has_note_incident_with_person1`
    FOREIGN KEY (`incident_with_person_person_id` , `incident_with_person_incident_id`)
    REFERENCES `incident_with_person` (`person_id` , `incident_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_with_person_has_note_note1`
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
-- Data for table `ethnicity`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `ethnicity` (`id`, `name`) VALUES (1, 'White');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (2, 'Black');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (3, 'Hispanic');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (4, 'Asian');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (5, 'American Indian');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (6, 'Pacific Islander');
INSERT INTO `ethnicity` (`id`, `name`) VALUES (7, 'Other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `person`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (1, NULL, DEFAULT, 'William ', 'Aaron', 'Padget', NULL, '1988-06-27', 'Male', 'Police Supervisor', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (2, NULL, DEFAULT, 'Omar ', NULL, 'Hernandez', 'jr', '1995-03-15', 'Male', 'Police Officer', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (3, NULL, DEFAULT, 'Steven', 'Adam', 'Burris', NULL, '1990-02-07', 'Male', 'Police Officer', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (4, NULL, DEFAULT, 'John', 'Adams', 'Terry', NULL, '1992-07-01', 'Male', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (5, NULL, DEFAULT, 'Morty', NULL, 'Smith', NULL, '2000-09-12', 'Male', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (6, NULL, DEFAULT, 'Rick', NULL, 'Sanchez', NULL, '1975-01-21', 'Male', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (7, NULL, DEFAULT, 'Summer', NULL, 'Smith', NULL, '1995-03-22', 'Female', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (8, NULL, DEFAULT, 'Tammy', NULL, 'Guetermann', NULL, '1982-02-01', 'Female', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (9, NULL, DEFAULT, 'Toby ', NULL, 'Matthews', NULL, '1985-06-19', 'Male', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (10, NULL, DEFAULT, 'Ruben', NULL, 'Ramirez', NULL, '1980-11-12', 'Male', 'Contacted Person', DEFAULT, DEFAULT);
INSERT INTO `person` (`id`, `record_id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`, `archived`) VALUES (11, NULL, DEFAULT, 'Mark', NULL, 'Thronson', 'jr', '1990-09-10', 'Male', 'Contacted Person', DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `user` (`id`, `person_id`, `permission_level`, `username`, `password`, `active`) VALUES (1, 1, 'supervisor', 'policesupervisor', 'supervisor', 1);
INSERT INTO `user` (`id`, `person_id`, `permission_level`, `username`, `password`, `active`) VALUES (2, 2, 'officer', 'policeofficer', 'officer', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (1, NULL, 'Employer', '9551 Civic Center Dr', NULL, 'Thornton', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (2, NULL, 'Residence', '9025 W Jefferson Ave', NULL, 'Denver', 'CO', 80235, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (3, NULL, 'Business', '9901 Grant St', NULL, 'Thornton ', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (4, NULL, 'Residence', '9656 Lane St', NULL, 'Denver', 'CO', 80260, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (5, NULL, 'Residence', '9967 Clayton St', NULL, 'Denver', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (6, NULL, 'Business', '10003 Grant St', NULL, 'Thornton', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (7, NULL, 'Residence', '8873 Colorado Ave', '207', 'Denver', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (8, NULL, 'Residence', '9220 Wigham St', NULL, 'Denver', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (9, NULL, 'Residence', '5041 Decatur St', NULL, 'Denver', 'CO', 80221, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (10, NULL, 'Business', '771 Thornton Pwky', NULL, 'Thornton', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (11, NULL, 'Residence', '9811 E Jewell Ave', NULL, 'Denver', 'CO', 80210, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (12 , NULL, 'Residence ', '8091 W 9th Ave', NULL, 'Denver', 'CO', 80214, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (13, NULL, 'Residence', '7351 E Warren Ave', NULL, 'Denver', 'CO', 80231, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (14, NULL, 'Residence', '5537 Yost Ct ', NULL, 'Denver', 'CO', 80239, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (15 , NULL, 'Residence', '43 S Ogden St ', NULL, 'Denver ', 'CO', 80209, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (16, NULL, 'Residence', '3855 S Monaco Pkwy', NULL, 'Denver', 'CO', 80237, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (17, NULL, 'Residence', '1771 W 72nd Ave', NULL, 'Denver', 'CO', 80221, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (18, NULL, 'Residence', '13692 W Utah Cir', NULL, 'Denver', 'CO', 80228, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (19, NULL, 'Residence', '1585 Kipling St', NULL, 'Denver', 'CO', 80215, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (20 , NULL, 'Residence', '1201 W Thornotn Pkwy', NULL, 'Thornton', 'CO', 80260, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (21, NULL, 'Business', '750 E 104th Ave', NULL, 'Thornton', 'CO', 80233, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (22, NULL, 'Street', '700 block of Eppinger Blvd', NULL, 'Thornton', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (23, 1, 'ARCHIVE', 'ARCHIVED ST', '', 'ARCHIVE', 'CO', 80229, DEFAULT, DEFAULT);
INSERT INTO `address` (`id`, `record_id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`, `archived`) VALUES (24, 1, 'ARCHIVE', 'ARCHIVE', NULL, 'ARCHIVE', 'CO', 80229, DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `person_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (1, 1);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (2, 1);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (3, 1);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (4, 2);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (5, 4);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (6, 4);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (7, 5);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (8, 7);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (9, 8);
INSERT INTO `person_address` (`person_id`, `address_id`) VALUES (10, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `case_file`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `case_file` (`id`, `record_id`, `suspected_crime`, `description`, `flag`, `archived`) VALUES (1, NULL, 'Shoplifting', 'Shoplifing', DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `incident`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `incident` (`id`, `record_id`, `address_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`, `archived`) VALUES (1, NULL, 3, 1, 'Dispatched call for service', 'Walmart', '2022-04-22', 'Shoplift', DEFAULT, DEFAULT);
INSERT INTO `incident` (`id`, `record_id`, `address_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`, `archived`) VALUES (2, NULL, 6, NULL, 'Suspicious person diggin through trash', 'Home Depot', '2022-04-20', 'Illegal dumping', DEFAULT, DEFAULT);
INSERT INTO `incident` (`id`, `record_id`, `address_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`, `archived`) VALUES (3, NULL, 6, 1, 'Dispatched call for service', 'Home Depot ', '2022-04-19', 'Shoplift', DEFAULT, DEFAULT);
INSERT INTO `incident` (`id`, `record_id`, `address_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`, `archived`) VALUES (4, NULL, 22, NULL, 'Ran a stop sign ', 'Intersection', '2022-04-19', 'Stop Sign violation', DEFAULT, DEFAULT);
INSERT INTO `incident` (`id`, `record_id`, `address_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`, `archived`) VALUES (5, NULL, 20, NULL, 'Matched description of suspect', 'Residence', '2022-04-20', 'Suspicious person ', DEFAULT, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `incident_with_person`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (4, 1, NULL, NULL, 'Shoplift', 20, 25, 'Suspect of shoplifting power tools from Walmart', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (2, 1, NULL, NULL, '', 1, 50, NULL, DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (3, 2, NULL, NULL, 'Suspicious person', 1, 50, '', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (6, 2, NULL, NULL, 'Suspicious person', 35, 45, 'person digging in trash', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (4, 3, NULL, NULL, 'Shoplift', 20, 25, 'Suspect of shoplifting power tools', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (8, 4, NULL, NULL, 'Stop Sign vioaltion', 30, 40, 'person ran a stop sign', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (3, 4, NULL, NULL, NULL, 1, 50, NULL, DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (3, 3, NULL, NULL, NULL, 1, 50, NULL, DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (7, 5, NULL, NULL, 'Suspicous person', 25, 35, 'matched description of suspect', DEFAULT);
INSERT INTO `incident_with_person` (`person_id`, `incident_id`, `record_id_person_id`, `record_id_incident_id`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`, `archived`) VALUES (2, 5, NULL, NULL, NULL, 1, 50, NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `department`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `department` (`id`, `address_id`, `name`) VALUES (1, 23, 'SKILL DISTILLERY PD');

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `note` (`id`, `record_id`, `user_id`, `created`, `updated`, `content`, `archived`) VALUES (1, NULL, 1, NULL, NULL, 'CONTENT', DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `incident_note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `incident_note` (`incident_id`, `note_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `case_file_note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `case_file_note` (`case_id`, `note_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `person_note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `person_note` (`person_id`, `note_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `officer`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `officer` (`id`, `record_id`, `person_id`, `supervisor_id`, `badge`, `image_url`, `archived`) VALUES (1, NULL, 1, NULL, '2201', NULL, DEFAULT);
INSERT INTO `officer` (`id`, `record_id`, `person_id`, `supervisor_id`, `badge`, `image_url`, `archived`) VALUES (2, NULL, 2, 1, '2202', NULL, DEFAULT);
INSERT INTO `officer` (`id`, `record_id`, `person_id`, `supervisor_id`, `badge`, `image_url`, `archived`) VALUES (3, NULL, 3, 1, '2001', NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `department_employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `department_employee` (`officer_id`, `department_id`) VALUES (1, 1);

COMMIT;

