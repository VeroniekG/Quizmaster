-- MySQL Script generated by MySQL Workbench
-- Wed Oct 21 17:46:57 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Quizmaster
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Quizmaster
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Quizmaster` DEFAULT CHARACTER SET utf8 ;
USE `Quizmaster` ;

-- -----------------------------------------------------
-- Table `Quizmaster`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`User` (
  `idUser` INT NOT NULL,
  `userName` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Role` (
  `idRole` INT NOT NULL,
  `description` VARCHAR(20) NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idRole`),
  INDEX `fk_Role_User1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Role_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Quizmaster`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Course` (
  `idCourse` INT NOT NULL,
  `courseName` VARCHAR(45) NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idCourse`),
  INDEX `fk_Course_User1_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Course_User1`
    FOREIGN KEY (`idUser`)
    REFERENCES `Quizmaster`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Quiz`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Quiz` (
  `idQuiz` INT NOT NULL,
  `idCursus` INT NOT NULL,
  PRIMARY KEY (`idQuiz`),
  INDEX `fk_Quizzes_Course1_idx` (`idCursus` ASC) VISIBLE,
  CONSTRAINT `fk_Quizzes_Course1`
    FOREIGN KEY (`idCursus`)
    REFERENCES `Quizmaster`.`Course` (`idCourse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Question` (
  `idQuestion` INT NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `Quizzes_idQuizzes` INT NOT NULL,
  `idAnswer` INT NOT NULL,
  `answerRight` VARCHAR(45) NOT NULL,
  `answerWrong1` VARCHAR(45) NOT NULL,
  `answerWrong2` VARCHAR(45) NOT NULL,
  `answerWrong3` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idQuestion`),
  INDEX `fk_Question_Quizzes1_idx` (`Quizzes_idQuizzes` ASC) VISIBLE,
  CONSTRAINT `fk_Question_Quizzes1`
    FOREIGN KEY (`Quizzes_idQuizzes`)
    REFERENCES `Quizmaster`.`Quiz` (`idQuiz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`StudentGroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`StudentGroup` (
  `idStudentGroup` INT NOT NULL,
  `StudentGroupName` VARCHAR(20) NOT NULL,
  `idCourse` INT NOT NULL,
  PRIMARY KEY (`idStudentGroup`),
  INDEX `fk_Group_Course_idx` (`idCourse` ASC) VISIBLE,
  CONSTRAINT `fk_Group_Course`
    FOREIGN KEY (`idCourse`)
    REFERENCES `Quizmaster`.`Course` (`idCourse`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Privilege`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Privilege` (
  `idPrivilege` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPrivilege`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`QuizResult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`QuizResult` (
  `idQuizResult` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  `Quizzes_idQuizzes` INT NOT NULL,
  `feedback` VARCHAR(100) NOT NULL,
  `dateTime` DATETIME NOT NULL,
  `timesCompleted` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idQuizResult`),
  INDEX `fk_QuizResult_User1_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_QuizResult_Quizzes1_idx` (`Quizzes_idQuizzes` ASC) VISIBLE,
  CONSTRAINT `fk_QuizResult_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Quizmaster`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_QuizResult_Quizzes1`
    FOREIGN KEY (`Quizzes_idQuizzes`)
    REFERENCES `Quizmaster`.`Quiz` (`idQuiz`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Quizmaster`.`Privilege_has_Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Quizmaster`.`Privilege_has_Role` (
  `idPrivilege` INT NOT NULL,
  `idRole` INT NOT NULL,
  PRIMARY KEY (`idPrivilege`, `idRole`),
  INDEX `fk_Privilege_has_Role_Role1_idx` (`idRole` ASC) VISIBLE,
  INDEX `fk_Privilege_has_Role_Privilege1_idx` (`idPrivilege` ASC) VISIBLE,
  CONSTRAINT `fk_Privilege_has_Role_Privilege1`
    FOREIGN KEY (`idPrivilege`)
    REFERENCES `Quizmaster`.`Privilege` (`idPrivilege`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Privilege_has_Role_Role1`
    FOREIGN KEY (`idRole`)
    REFERENCES `Quizmaster`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Quizmaster`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`) VALUES (1, 'UserName1', 'PW1');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`) VALUES (2, 'UserName2', 'PW2');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`) VALUES (3, 'UserName3', 'PW3');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`) VALUES (4, 'UserName4', 'PW4');
INSERT INTO `Quizmaster`.`User` (`idUser`, `userName`, `password`) VALUES (5, 'UserName5', 'PW5');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Role` (`idRole`, `description`, `idUser`) VALUES (1, 'Student', 1);
INSERT INTO `Quizmaster`.`Role` (`idRole`, `description`, `idUser`) VALUES (2, 'Docent', 2);
INSERT INTO `Quizmaster`.`Role` (`idRole`, `description`, `idUser`) VALUES (3, 'Coordinator', 3);
INSERT INTO `Quizmaster`.`Role` (`idRole`, `description`, `idUser`) VALUES (4, 'Administrator ', 4);
INSERT INTO `Quizmaster`.`Role` (`idRole`, `description`, `idUser`) VALUES (5, 'Technisch beheerder', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Course`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Course` (`idCourse`, `courseName`, `idUser`) VALUES (1, 'Course1', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Quiz`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Quiz` (`idQuiz`, `idCursus`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Question`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Question` (`idQuestion`, `description`, `Quizzes_idQuizzes`, `idAnswer`, `answerRight`, `answerWrong1`, `answerWrong2`, `answerWrong3`) VALUES (1, 'Wat is 1+1?', 1, 1, '2', '3', '4', '5');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`StudentGroup`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`StudentGroup` (`idStudentGroup`, `StudentGroupName`, `idCourse`) VALUES (1, 'Group1', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Privilege`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Privilege` (`idPrivilege`, `description`) VALUES (1, 'Toegang1');
INSERT INTO `Quizmaster`.`Privilege` (`idPrivilege`, `description`) VALUES (2, 'Toegang2');
INSERT INTO `Quizmaster`.`Privilege` (`idPrivilege`, `description`) VALUES (3, 'Toegang3');
INSERT INTO `Quizmaster`.`Privilege` (`idPrivilege`, `description`) VALUES (4, 'Toegang4');
INSERT INTO `Quizmaster`.`Privilege` (`idPrivilege`, `description`) VALUES (5, 'Toegang5');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`QuizResult`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`QuizResult` (`idQuizResult`, `User_idUser`, `Quizzes_idQuizzes`, `feedback`, `dateTime`, `timesCompleted`) VALUES (1, 1, 1, 'Geslaagd', '2020-10-10', '1');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Quizmaster`.`Privilege_has_Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `Quizmaster`;
INSERT INTO `Quizmaster`.`Privilege_has_Role` (`idPrivilege`, `idRole`) VALUES (1, 1);
INSERT INTO `Quizmaster`.`Privilege_has_Role` (`idPrivilege`, `idRole`) VALUES (2, 2);
INSERT INTO `Quizmaster`.`Privilege_has_Role` (`idPrivilege`, `idRole`) VALUES (3, 3);
INSERT INTO `Quizmaster`.`Privilege_has_Role` (`idPrivilege`, `idRole`) VALUES (4, 4);
INSERT INTO `Quizmaster`.`Privilege_has_Role` (`idPrivilege`, `idRole`) VALUES (5, 5);

COMMIT;
