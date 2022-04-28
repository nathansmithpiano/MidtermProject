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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `person` ;

CREATE TABLE IF NOT EXISTS `person` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'User isA Person\\n',
  `ethnicity_id` INT NOT NULL DEFAULT 7,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `birthdate` DATE NULL,
  `gender` VARCHAR(45) NOT NULL DEFAULT 'UNKOWN',
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_person_ethnicity1_idx` (`ethnicity_id` ASC),
  CONSTRAINT `fk_person_ethnicity1`
    FOREIGN KEY (`ethnicity_id`)
    REFERENCES `ethnicity` (`id`)
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
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `street1` VARCHAR(255) NULL DEFAULT NULL,
  `street2` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state_code` VARCHAR(2) NULL DEFAULT NULL,
  `zip` INT(5) UNSIGNED NULL DEFAULT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
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
  `case_number` INT UNSIGNED NOT NULL,
  `suspected_crime` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `case_number_UNIQUE` (`case_number` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `officer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `officer` ;

CREATE TABLE IF NOT EXISTS `officer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `supervisor_id` INT NULL,
  `badge` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_officer_officer1_idx` (`supervisor_id` ASC),
  UNIQUE INDEX `badge_UNIQUE` (`badge` ASC),
  CONSTRAINT `fk_officer_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_officer_officer1`
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `officer` (`id`)
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
-- Table `incident`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident` ;

CREATE TABLE IF NOT EXISTS `incident` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address_id` INT NOT NULL,
  `officer_id` INT NOT NULL,
  `department_id` INT NOT NULL,
  `case_id` INT NULL,
  `reason_for_contact` VARCHAR(45) NULL DEFAULT NULL,
  `location` VARCHAR(45) NULL DEFAULT NULL,
  `incident_date` DATETIME NULL,
  `description` TEXT NULL,
  `flag` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_incident_address1_idx` (`address_id` ASC),
  INDEX `fk_incident_case1_idx` (`case_id` ASC),
  INDEX `fk_incident_officer1_idx` (`officer_id` ASC),
  INDEX `fk_incident_department1_idx` (`department_id` ASC),
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
  CONSTRAINT `fk_incident_officer1`
    FOREIGN KEY (`officer_id`)
    REFERENCES `officer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_incident_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `incident_person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `incident_person` ;

CREATE TABLE IF NOT EXISTS `incident_person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `incident_id` INT NOT NULL,
  `ethnicity_id` INT NOT NULL,
  `gender` VARCHAR(45) NULL,
  `suspected_crime` VARCHAR(45) NULL,
  `age_minimum` INT NULL,
  `age_maximum` INT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`person_id`, `incident_id`),
  INDEX `fk_person_has_incident_incident1_idx` (`incident_id` ASC),
  INDEX `fk_person_has_incident_person1_idx` (`person_id` ASC),
  INDEX `fk_incident_person_ethnicity1_idx` (`ethnicity_id` ASC),
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
  CONSTRAINT `fk_incident_person_ethnicity1`
    FOREIGN KEY (`ethnicity_id`)
    REFERENCES `ethnicity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `note` ;

CREATE TABLE IF NOT EXISTS `note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `created` TIMESTAMP NULL,
  `updated` TIMESTAMP NULL,
  `content` TEXT NULL,
  PRIMARY KEY (`id`))
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
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (1, 1, 'William', 'Aaron', 'Padget', NULL, '1988-06-27', 'Male', 'Police Supervisor', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (2, 3, 'Omar', NULL, 'Hernandez', 'jr', '1995-03-15', 'Male', 'Police Officer', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (3, 1, 'Steven', 'Adam', 'Burris', NULL, '1990-02-07', 'Male', 'Police Officer', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (4, 1, 'John', NULL, 'Terry', NULL, '1992-07-01', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (5, 1, 'Morty', NULL, 'Smith', NULL, '2000-09-12', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (6, 1, 'Rick', NULL, 'Sanchez', NULL, '1975-01-21', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (7, 1, 'Summer', NULL, 'Smith', NULL, '1995-03-22', 'Female', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (8, 1, 'Tammy', NULL, 'Guetermann', NULL, '1982-02-01', 'Female', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (9, 1, 'Toby', NULL, 'Matthews', NULL, '1985-06-19', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (10, 3, 'Ruben', NULL, 'Ramirez', NULL, '1980-11-12', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (11, 1, 'Mark', NULL, 'Thronson', 'jr', '1990-09-10', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (12, 2, 'Mary', 'Sue', 'Wyatt', NULL, '1985-04-13', 'Female', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (13, 1, 'Justine', 'Ray', 'Rustin', NULL, '1994-01-01', 'Female', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (14, 1, 'Daniel', NULL, 'McDaniel', NULL, '1953-10-30', 'Male', 'Contacted person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (15, 1, 'Beverly', 'Mary', 'Neal', NULL, '1973-05-19', 'Female', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (16, 2, 'Larry', 'Isaac', 'Paterson', NULL, '2001-11-17', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (17, 3, 'Charles', NULL, 'Rodrigues', NULL, '1982-01-25', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (18, 1, 'Christopher', NULL, 'Scanlan', NULL, '1979-07-23', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (19, 1, 'Doug', 'John', 'Johnston', NULL, '2003-01-03', 'Male', 'Contacted Person', 0);
INSERT INTO `person` (`id`, `ethnicity_id`, `first_name`, `middle_name`, `last_name`, `title`, `birthdate`, `gender`, `description`, `flag`) VALUES (20, 1, 'Lee', NULL, 'Bradley', NULL, '1968-09-28', 'Male', 'Contacted Person', 0);

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
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (1, 'Employer', '9551 Civic Center Dr', NULL, 'Thornton', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (2, 'Residence', '9025 W Jefferson Ave', NULL, 'Denver', 'CO', 80235, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (3, 'Business', '9901 Grant St', NULL, 'Thornton', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (4, 'Residence', '9656 Lane St', NULL, 'Denver', 'CO', 80260, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (5, 'Residence', '9967 Clayton St', NULL, 'Denver', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (6, 'Business', '10003 Grant St', NULL, 'Thornton', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (7, 'Residence', '8873 Colorado Ave', '207', 'Denver', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (8, 'Residence', '9220 Wigham St', NULL, 'Denver', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (9, 'Residence', '5041 Decatur St', NULL, 'Denver', 'CO', 80221, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (10, 'Business', '771 Thornton Pwky', NULL, 'Thornton', 'CO', 80229, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (11, 'Residence', '9811 E Jewell Ave', NULL, 'Denver', 'CO', 80210, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (12 , 'Residence ', '8091 W 9th Ave', NULL, 'Denver', 'CO', 80214, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (13, 'Residence', '7351 E Warren Ave', NULL, 'Denver', 'CO', 80231, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (14, 'Residence', '5537 Yost Ct', NULL, 'Denver', 'CO', 80239, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (15 , 'Residence', '43 S Ogden St', NULL, 'Denver', 'CO', 80209, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (16, 'Residence', '3855 S Monaco Pkwy', NULL, 'Denver', 'CO', 80237, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (17, 'Residence', '1771 W 72nd Ave', NULL, 'Denver', 'CO', 80221, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (18, 'Residence', '13692 W Utah Cir', NULL, 'Denver', 'CO', 80228, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (19, 'Residence', '1585 Kipling St', NULL, 'Denver', 'CO', 80215, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (20 , 'Residence', '1201 W Thornotn Pkwy', NULL, 'Thornton', 'CO', 80260, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (21, 'Business', '750 E 104th Ave', NULL, 'Thornton', 'CO', 80233, 0);
INSERT INTO `address` (`id`, `description`, `street1`, `street2`, `city`, `state_code`, `zip`, `flag`) VALUES (22, 'Street', '700 block of Eppinger Blvd', NULL, 'Thornton', 'CO', 80229, 0);

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
INSERT INTO `case_file` (`id`, `case_number`, `suspected_crime`, `description`, `flag`) VALUES (1, 2022001, 'Shoplifting', 'Shoplifing', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `officer`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `officer` (`id`, `person_id`, `supervisor_id`, `badge`, `image_url`) VALUES (1, 1, NULL, '2201', NULL);
INSERT INTO `officer` (`id`, `person_id`, `supervisor_id`, `badge`, `image_url`) VALUES (2, 2, 1, '2202', NULL);
INSERT INTO `officer` (`id`, `person_id`, `supervisor_id`, `badge`, `image_url`) VALUES (3, 3, 1, '2001', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `department`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `department` (`id`, `address_id`, `name`) VALUES (1, 1, 'SKILL DISTILLERY PD');

COMMIT;


-- -----------------------------------------------------
-- Data for table `incident`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (1, 3, 2, 1, 1, 'Dispatched call for service', 'Walmart', '2022-04-22', 'Shoplift', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (2, 6, 3, 1, NULL, 'Suspicious person diggin through trash', 'Home Depot', '2022-04-20', 'Illegal dumping', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (3, 6, 2, 1, 1, 'Dispatched call for service', 'Home Depot', '2022-04-19', 'Shoplift', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (4, 22, 3, 1, NULL, 'Ran a stop sign ', 'Intersection', '2022-04-19', 'Stop Sign violation', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (5, 20, 2, 1, NULL, 'Matched description of suspect', 'Residence', '2022-04-20', 'Suspicious person ', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (6, 21, 1, 1, NULL, 'disruptive person in store', 'Safeway', '2022-04-21', 'disruptive person', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (7, 3, 2, 1, NULL, 'Robbery', 'Walmart', '2022-04-17', 'shoplift turned robbery', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (8, 11, 2, 1, NULL, 'Family disturbance', 'Residence', '2022-04-22', 'Adult son, drunk and disruptive', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (9, 21, 2, 1, NULL, 'Homless party', 'Safeway', '2022-04-23', 'Homless person sleeping in bathroom', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (10, 16, 2, 1, NULL, 'Waved-down', 'Residence', '2022-04-23', 'waved down by concerned citizen', 0);
INSERT INTO `incident` (`id`, `address_id`, `officer_id`, `department_id`, `case_id`, `reason_for_contact`, `location`, `incident_date`, `description`, `flag`) VALUES (11, 12, 2, 1, NULL, 'Car-accident', 'Residence', '2022-04-23', 'car accident in front of house', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `incident_person`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (4, 1, 1, NULL, 'Shoplift', 20, 25, 'Suspect of shoplifting power tools from Walmart');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (2, 1, 3, NULL, '', 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (3, 2, 1, NULL, 'Suspicious person', 1, 50, '');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (6, 2, 1, NULL, 'Suspicious person', 35, 45, 'person digging in trash');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (4, 3, 1, NULL, 'Shoplift', 20, 25, 'Suspect of shoplifting power tools');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (8, 4, 1, NULL, 'Stop Sign vioaltion', 30, 40, 'person ran a stop sign');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (3, 4, 1, NULL, NULL, 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (3, 3, 1, NULL, NULL, 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (7, 5, 1, NULL, 'Suspicous person', 25, 35, 'matched description of suspect');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (2, 5, 3, NULL, NULL, 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (2, 3, 3, NULL, 'Shoplift', 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (1, 6, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (6, 6, 1, NULL, 'Disruptive person', 20, 30, 'verbal disturbance inside store');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (2, 7, 1, NULL, NULL, 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (4, 7, 1, NULL, 'Robbery', NULL, NULL, 'pulled knife on loss-prevention');
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (2, 8, 1, NULL, NULL, 1, 50, NULL);
INSERT INTO `incident_person` (`person_id`, `incident_id`, `ethnicity_id`, `gender`, `suspected_crime`, `age_minimum`, `age_maximum`, `description`) VALUES (11, 8, 1, NULL, 'Family Disturbance', 20, 30, 'drunk adult male');

COMMIT;


-- -----------------------------------------------------
-- Data for table `note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (1, 1, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT1');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (2, 2, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT2');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (3, 2, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT3');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (4, 2, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT4');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (5, 3, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT5');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (6, 1, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT6');
INSERT INTO `note` (`id`, `user_id`, `created`, `updated`, `content`) VALUES (7, 2, '2022-04-22 12:00:01', '2022-04-22 12:27:00', 'CONTENT7');

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
INSERT INTO `case_file_note` (`case_id`, `note_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `person_note`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `person_note` (`person_id`, `note_id`) VALUES (4, 3);
INSERT INTO `person_note` (`person_id`, `note_id`) VALUES (1, 7);
INSERT INTO `person_note` (`person_id`, `note_id`) VALUES (2, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `department_employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `eleidb`;
INSERT INTO `department_employee` (`officer_id`, `department_id`) VALUES (1, 1);
INSERT INTO `department_employee` (`officer_id`, `department_id`) VALUES (2, 1);
INSERT INTO `department_employee` (`officer_id`, `department_id`) VALUES (3, 1);

COMMIT;

